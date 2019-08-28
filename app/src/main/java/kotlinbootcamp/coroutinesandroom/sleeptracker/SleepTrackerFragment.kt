/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kotlinbootcamp.coroutinesandroom.sleeptracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kotlinbootcamp.R
import com.kotlinbootcamp.databinding.FragmentSleepTrackerNewBinding
import kotlinbootcamp.coroutinesandroom.database.SleepDatabase

/**
 * A fragment with buttons to record start and end times for sleep, which are saved in
 * a database. Cumulative data is displayed in a simple scrollable TextView.
 * (Because we have not learned about RecyclerView yet.)
 */
class SleepTrackerFragment : Fragment() {

    /**
     * Called when the Fragment is ready to display content to the screen.
     *
     * This function uses DataBindingUtil to inflate R.layout.fragment_sleep_quality.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentSleepTrackerNewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sleep_tracker_new, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepTrackerViewModelFactory(dataSource, application)
        val sleepTrackerViewModel = ViewModelProviders.of(this, viewModelFactory).get(SleepTrackerViewModel::class.java)
        val gridLayoutManager = GridLayoutManager(activity, 3)

        val adapter = SleepNightAdapter(SleepNightListener { nightId ->
            Toast.makeText(context, "$nightId", Toast.LENGTH_LONG).show()
        })

        setUpVMCallbacks(sleepTrackerViewModel, adapter)

        binding.sleepTrackerViewModel = sleepTrackerViewModel
        binding.sleepList.adapter = adapter
        binding.sleepList.layoutManager = gridLayoutManager
        binding.lifecycleOwner = this

        return binding.root
    }

    private fun setUpVMCallbacks(sleepTrackerViewModel: SleepTrackerViewModel, adapter: SleepNightAdapter) {
        sleepTrackerViewModel.nights.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        sleepTrackerViewModel.navigateToSleepQuality.observe(this, Observer { night ->
            night?.let {
                this.findNavController().navigate(SleepTrackerFragmentDirections.actionSleepTrackerFragmentToSleepQualityFragment(night.nightId))
                sleepTrackerViewModel.doneNavigation()
            }
        })

        sleepTrackerViewModel.navigateToSleepDetail.observe(this, Observer { night ->
            night?.let {
                this.findNavController().navigate(SleepTrackerFragmentDirections.actionSleepTrackerFragmentToSleepDetailFragment(night))
                sleepTrackerViewModel.onSleepDetailNavigated()
            }
        })

        sleepTrackerViewModel.showSnackBarEvent.observe(this, Observer {
            if (it == true) { // Observed state is true.
                Snackbar.make(activity!!.findViewById(android.R.id.content), getString(R.string.cleared_message), Snackbar.LENGTH_SHORT).show()
                sleepTrackerViewModel.doneShowingSnackbar()
            }
        })

    }
}
