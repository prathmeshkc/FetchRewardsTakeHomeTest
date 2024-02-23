package com.pcandroiddev.fetchrewardstakehometest.util

import com.pcandroiddev.fetchrewardstakehometest.data.local.CollapsableItem
import com.pcandroiddev.fetchrewardstakehometest.data.remote.Item

object Helper {
    /**
     * 1. First filter the Items with blank/empty/null name
     * 2. Group the items by listId. This will give us a map where the key is the listId and the value is a list of items with that listId
     * 3. Sort the map by listId (keys)
     * 4. Sort the items in the list by name
     */

    fun fromItemListToCollapsableListItem(itemList: List<Item>): List<CollapsableItem> {

        val filteredItemList = itemList.filter { !it.name.isNullOrBlank() }
        val listIdToItemsMap = filteredItemList.groupBy { it.listId }
        val sortedMapValues =
            listIdToItemsMap.mapValues { (_, value) -> value.sortedBy { it.name } }
        val sortedByListId = sortedMapValues.toSortedMap()

        return sortedByListId.map { (listId, items) ->
            CollapsableItem(
                listId = listId,
                itemsList = items
            )
        }
    }

}