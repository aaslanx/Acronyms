package com.example.acronyms.screens

import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.acronyms.R
import com.example.acronyms.adapters.AcronymsAdapter
import com.example.acronyms.databinding.FragmentAcronymsBinding
import com.example.acronyms.model.AcronymResponse
import com.example.acronyms.model.Lf
import com.example.acronyms.utils.capitalized
import com.example.acronyms.utils.hideKeyboard
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AcronymsFragment : Fragment() {

    lateinit var binding: FragmentAcronymsBinding
    private var acronymList = emptyList<AcronymResponse>()
    private val viewModel: AcronymViewModel by viewModels()
    private var adapter: AcronymsAdapter? = null


    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_acronyms, container, false)

        with(binding) {
            searchButton.setOnClickListener {
                val sf = searchTextInput.text
                searchTextInput.clearFocus()
                requireContext().hideKeyboard(searchTextInput)

                searchTextInput.text.apply {
                    if (!this.isNullOrBlank()) {
                        lifecycleScope.launch {
                            acronymList =
                                withContext(Dispatchers.IO) { viewModel.getAcronyms(sf.toString()) }

                            adapter = AcronymsAdapter {
                                it.vars?.let { list -> showBottomSheet(list, it.lf) }
                            }
                            recycler.layoutManager =
                                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                            recycler.adapter = adapter

                            if (acronymList.isNotEmpty()) {
                                adapter?.submitData(acronymList[0].lfs)
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    getString(R.string.error_no_result),
                                    Toast.LENGTH_SHORT
                                ).show()
                                this@apply.clear()
                            }
                        }
                    }
                }
            }
        }

        return binding.root
    }

    private fun showBottomSheet(list: List<Lf>, parentLongForm: String) = with(binding) {
        bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.DialogSlideAnim)
        bottomSheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_layout)

        val window: Window = bottomSheetDialog.window!!
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.BOTTOM)

        val title: TextView = window.findViewById(R.id.parentLongForm)
        val bottomAdapter = AcronymsAdapter { bottomSheetDialog.dismiss() }

        var mRecyclerView: RecyclerView = window.findViewById(R.id.bottom_sheet_list_view)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mRecyclerView.adapter = bottomAdapter

        bottomAdapter.submitData(list)
        title.text = parentLongForm.capitalized()
        bottomSheetDialog.show()
    }
}