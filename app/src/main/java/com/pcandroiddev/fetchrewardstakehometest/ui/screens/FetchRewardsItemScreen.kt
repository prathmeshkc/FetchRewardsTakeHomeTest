package com.pcandroiddev.fetchrewardstakehometest.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pcandroiddev.fetchrewardstakehometest.Greeting
import com.pcandroiddev.fetchrewardstakehometest.data.local.CollapsableItem
import com.pcandroiddev.fetchrewardstakehometest.ui.components.FetchRewardsItemList
import com.pcandroiddev.fetchrewardstakehometest.ui.theme.PurpleGrey80
import com.pcandroiddev.fetchrewardstakehometest.util.APIResult
import com.pcandroiddev.fetchrewardstakehometest.viewmodel.ItemsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FetchRewardsItemScreen(
    itemsViewModel: ItemsViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    var isLoading by remember { mutableStateOf(false) }

    var collapsableItemList by remember {
        mutableStateOf(emptyList<CollapsableItem>())
    }

    val listItems by itemsViewModel.collapsableItems.collectAsState()

    when (listItems) {
        is APIResult.Loading -> {
            isLoading = true
        }

        is APIResult.Success -> {
            isLoading = false
            collapsableItemList = listItems.data!!
        }

        is APIResult.Error -> {
            isLoading = false
            Toast.makeText(context, listItems.message.toString(), Toast.LENGTH_LONG)
                .show()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = "Fetch",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
                }
            )
        }
    ) { innerPadding ->

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = PurpleGrey80,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                if (collapsableItemList.isNotEmpty()) {
                    FetchRewardsItemList(
                        collapsableItems = collapsableItemList,
                    )
                }
            }
        }
    }
}
