package com.pcandroiddev.fetchrewardstakehometest.ui.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pcandroiddev.fetchrewardstakehometest.data.local.CollapsableItem
import com.pcandroiddev.fetchrewardstakehometest.data.remote.Item
import com.pcandroiddev.fetchrewardstakehometest.ui.theme.Purple80
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun SubItem(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    )
}

@Composable
fun CollapsableItemHeader(
    text: String,
    isExpanded: Boolean = false,
    onCollapseIconClicked: () -> Unit
) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Purple80)
            .border(border = BorderStroke(width = 1.dp, color = Color.Black))
            .padding(16.dp)
            .clickable {
                onCollapseIconClicked()
            }
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
        )

        Icon(
            imageVector = if (isExpanded) Icons.Outlined.KeyboardArrowUp else Icons.Outlined.KeyboardArrowDown,
            contentDescription = "Collapse Icon",
            tint = Color.Black
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FetchRewardsItemList(
    collapsableItems: List<CollapsableItem>,
    modifier: Modifier = Modifier
) {
    Log.d("FetchRewardsItemList", "Size: ${collapsableItems.size}")

    val isExpandedList = remember {
        mutableStateListOf<Boolean>().apply {
            addAll(List(collapsableItems.size) { false })
        }
    }

    LazyColumn {
        Log.d("FetchRewardsItemList", "FetchRewardsItemList: $collapsableItems")
        collapsableItems.forEachIndexed { index, collapsableItem: CollapsableItem ->
            val isExpanded = isExpandedList[index]
            Log.d("FetchRewardsItemList", "Index: $index CollapsableItem: $collapsableItem")

            item {
                CollapsableItemHeader(
                    text = "List ${collapsableItem.listId}",
                    isExpanded = isExpanded,
                    onCollapseIconClicked = {
                        isExpandedList[index] = !isExpanded
                    }
                )
            }

            if (isExpanded) {
                items(collapsableItem.itemsList) { item: Item ->
                    AnimatedVisibility(visible = true) {
                        SubItem(text = "id: ${item.id} name: ${item.name ?: "Unknown"}")
                    }
                }
            }
        }
    }


}

