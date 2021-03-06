package renetik.android.sample.view.getpicture

import android.view.View
import android.widget.GridView
import renetik.android.framework.lang.CSLayoutRes.Companion.layout
import renetik.android.controller.base.CSActivityView
import renetik.android.controller.common.CSNavigationItem
import renetik.android.controller.extensions.dialog
import renetik.android.controller.extensions.floatingButton
import renetik.android.controller.extensions.imageView
import renetik.android.controller.extensions.textView
import renetik.android.dialog.showView
import renetik.android.getpicture.CSGetPictureView
import renetik.android.listview.CSListView
import renetik.android.listview.CSRowView
import renetik.android.listview.actions.CSRemoveListRowsView
import renetik.android.sample.R
import renetik.android.sample.model.ImageItem
import renetik.android.sample.model.model
import renetik.android.sample.view.navigation
import renetik.android.view.extensions.image
import renetik.android.view.extensions.imageView
import renetik.android.view.extensions.onClick
import renetik.android.view.extensions.text

class SampleGetPictureView(title: String)
    : CSActivityView<View>(navigation, layout(R.layout.sample_getpicture)), CSNavigationItem {

    private val grid = CSListView<ImageItem, GridView>(this, R.id.SampleGetPicture_Grid) {
        CSRowView(this, layout(R.layout.sample_getpicture_item)) { data ->
            imageView(R.id.SampleGetPictureItem_Image).image(data.image.value)
        }
    }.onItemClick { row ->
        dialog("Image detail").showView(R.layout.sample_getpicture_item)
            .imageView(R.id.SampleGetPictureItem_Image).image(row.row.image.value)
    }.emptyView(R.id.SampleGetPicture_ListEmpty)

    private val getPicture =
        CSGetPictureView(this, "Select photo or take picture", "Pictures") {
            model.getPictureList.add(ImageItem(it))
            model.save()
            reloadGrid()
        }

    init {
        textView(R.id.SampleGetPicture_Title).text(title)
        CSRemoveListRowsView(grid, "Remove selected items ?") { toRemove ->
            toRemove.forEach { item -> model.getPictureList.remove(item) }
            model.save()
            grid.reload(model.getPictureList.list)
        }
        floatingButton(R.id.SampleGetPicture_AddImageButton).onClick { getPicture.show() }
        reloadGrid()
    }

    private fun reloadGrid() = grid.reload(model.getPictureList.list)
}