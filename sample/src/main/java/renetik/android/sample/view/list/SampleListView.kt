package renetik.android.sample.view.list

import android.view.View
import android.widget.ListView
import renetik.android.framework.lang.CSLayoutRes.Companion.layout
import renetik.android.controller.base.CSActivityView
import renetik.android.controller.common.CSNavigationItem
import renetik.android.controller.common.CSSearchView
import renetik.android.controller.extensions.*
import renetik.android.dialog.showView
import renetik.android.java.extensions.collections.list
import renetik.android.listview.CSListView
import renetik.android.listview.CSRowView
import renetik.android.listview.actions.CSRemoveListRowsView
import renetik.android.logging.CSLog.logInfoToast
import renetik.android.sample.R
import renetik.android.sample.model.ListItem
import renetik.android.sample.model.model
import renetik.android.sample.view.navigation
import renetik.android.view.extensions.editText
import renetik.android.view.extensions.onClick
import renetik.android.view.extensions.text

class SampleListView(title: String)
    : CSActivityView<View>(navigation, layout(R.layout.sample_list)), CSNavigationItem {

    private val listController = CSListView<ListItem, ListView>(this, R.id.SampleList_List) {
        CSRowView(this, layout(R.layout.sample_list_item)) { row ->
            textView(R.id.header).text(row.time)
            textView(R.id.title).text(row.title)
            textView(R.id.subtitle).text(row.subtitle)
        }
    }.onItemClick { rowView -> snackBarInfo("SampleListItemView clicked ${rowView.row.title}") }
        .emptyView(R.id.SampleList_ListEmpty)
    private val searchController = CSSearchView(this) { reloadList() }

    init {
        textView(R.id.SampleList_Title).text(title)
        CSRemoveListRowsView(listController, "Remove selected items ?") { toRemove ->
            toRemove.forEach { item -> model.sampleList.remove(item) }
            model.save()
            listController.reload(model.sampleList.list)
        }
        menuItem(searchController.view)
        menuItem("Add Item").onClick { showAddItemDialog() }.alwaysAsAction()
        menuItem("Menu item 1").onClick { logInfoToast("This is item 1 click") }.neverAsAction()
        menuItem("Menu item 2").neverAsAction().onClick {
            dialog("This is item 2 click").show { dialog("and dialog Button click").show() }
        }
        button(R.id.SampleList_BottomButton).onClick { dialog("Bottom Button click").show() }
        reloadList()
    }

    private fun showAddItemDialog() =
        dialog("Add item to list").showView(R.layout.sample_pager_add_item) {
            val nameView = it.view.editText(R.id.SamplePagerAddItem_Name)
            val descView = it.view.editText(R.id.SamplePagerAddItem_Description)
            true //TODO Broken...
//            list(nameView, descView).validateNotEmpty("Cannot be empty") {
//                model.sampleList.add(ListItem(nameView.text(), descView.text()))
//                reloadList()
//            }
        }

    private fun reloadList() {
        listController.reload(
            if (searchController.text.isEmpty()) model.sampleList.list
            else list<ListItem>().apply {
                for (row in model.sampleList)
                    if (row.searchableText.contains(searchController.text, ignoreCase = true))
                        add(row)
            })
    }

    override val navigationItemTitle = "Renetik"
}