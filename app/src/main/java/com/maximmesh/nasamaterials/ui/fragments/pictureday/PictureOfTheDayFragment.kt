package com.maximmesh.nasamaterials.ui.fragments.pictureday

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import coil.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.maximmesh.nasamaterials.R
import com.maximmesh.nasamaterials.databinding.FragmentPictureOfTheDayBinding
import com.maximmesh.nasamaterials.repository.AppState
import com.maximmesh.nasamaterials.repository.network.entities.PictureOfDayDTO
import com.maximmesh.nasamaterials.utils.URI_WIKI
import com.maximmesh.nasamaterials.utils.toast

class PictureOfTheDayFragment : Fragment() {

    private var isZoomed = false
    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding get() = _binding!!
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(PictureOfTheDayViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getData().observe(viewLifecycleOwner) { renderData(it) }
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(URI_WIKI + binding.inputEditText.text.toString())
            })
        }
        enlargePictureFirst()
        animatePhotoClick()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Success -> {
                binding.progressBar.visibility = View.GONE
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    toast("Link is empty")
                } else {
                    binding.imageView.load(url) {
                        lifecycle(this@PictureOfTheDayFragment)
                        error(R.drawable.ic_load_error_vector) //если картинка не загрузится
                        placeholder(R.drawable.ic_no_photo_vector) //картинка во время загрузки основной
                        crossfade(true)
                    }
                    renderBottomSheet(serverResponseData)
                }
            }
            is AppState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                toast(data.error.message)
            }
        }
    }

    /** Произвел работу с текстом с SpannableStringBuilder
     * В renderBottomSheet в описании и в дате, что приходит с сервера,
     * все числа заменяются на цвет "still_black_but" из ресурсов,
     * так же как и  все заглавные буквы заменяются на такой же цвет.
     * В общей массе смена цвета сильно заметна не будет, тк я и не хотел бы менять цвет,
     * но сделать эту работу стоило бы, поэтому:
     */
    private fun renderBottomSheet(serverResponseData: PictureOfDayDTO) {
        (view?.findViewById(R.id.bottomSheetDescriptionHeader) as TextView).also {
            it.text = "${serverResponseData.title}"
        }
        (view?.findViewById(R.id.bottomSheetDescription) as TextView).also {
            with(serverResponseData)
            {
                val spannable = SpannableStringBuilder(
                    "${explanation}\n\n\n $date"
                )
                spannable.forEachIndexed { index, c ->
                    if (c.isDigit()) {
                        spannable.setSpan(
                            ForegroundColorSpan(
                                ResourcesCompat.getColor(resources, R.color.still_black_but, null)
                            ),
                            index,
                            index + 1,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    }
                    if (c.isUpperCase()) {
                        spannable.setSpan(
                            ForegroundColorSpan(
                                ResourcesCompat.getColor(resources, R.color.still_black_but, null)
                            ),
                            index,
                            index + 1,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    }
                }
                it.text = spannable
            }
        }
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun enlargePictureFirst() {
        binding.imageView.apply {
            scaleType = ImageView.ScaleType.CENTER
        }
    }

    /**Здесь задействовал анимацию. При первом нажатии на фотографию,
    она отдаляется, при повтороном нажатии приближается*/
    private fun animatePhotoClick() {
        binding.imageView.setOnClickListener {
            isZoomed = !isZoomed
            TransitionManager.beginDelayedTransition(
                binding.root,
                TransitionSet()
                    .addTransition(ChangeBounds())
                    .addTransition(ChangeImageTransform())
            )
            binding.imageView.apply {
                scaleType =
                    if (isZoomed) ImageView.ScaleType.CENTER_INSIDE else ImageView.ScaleType.CENTER_CROP
            }
        }
    }
}
