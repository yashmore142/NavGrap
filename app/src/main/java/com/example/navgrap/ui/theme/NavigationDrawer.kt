package com.example.navgrap.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.navgrap.NavDrawerItem
import com.example.navgrap.R
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val drawerList = listOf(
        NavDrawerItem(Icons.Default.Face, "Profile", "0", false),
        NavDrawerItem(Icons.Default.MailOutline, "Index", "32", true),
        NavDrawerItem(Icons.Default.Favorite, "Favourite", "50", true),
        NavDrawerItem(Icons.Default.Build, "LogOut", "0", false)
    )

    var selectedItem by remember {
        mutableStateOf(drawerList[0])
    }
    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var scope = rememberCoroutineScope()
    ModalNavigationDrawer(drawerContent = {
        ModalDrawerSheet {
            Column(
                modifier =
                Modifier
                    .fillMaxSize()
                    .width(200.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.Yellow),
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        modifier = Modifier.wrapContentSize(),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img),
                            contentDescription = "profile picture",
                            modifier = Modifier
                                .size(130.dp)
                                .clip(CircleShape)
                        )

                        Text(
                            text = "Yash More",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center
                        )

                    }
                    Divider(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        thickness = 2.dp,
                        color = Color.Green
                    )

                }
                drawerList.forEach {
                    NavigationDrawerItem(label = {
                        Text(text = it.text)
                    }, selected = it == selectedItem,
                        onClick = {
                            selectedItem = it
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        modifier = Modifier.padding(horizontal = 10.dp,
                            vertical = 10.dp),
                        icon = {
                            Icon(imageVector = it.image, contentDescription = it.text)
                        }, badge = {
                            if (it.hasBadge) {
                                BadgedBox(badge = {
                                    Badge {
                                        Text(text = it.badgeCount)
                                    }
                                }) {

                                }
                            }
                        })
                }
            }
        }
    },drawerState =drawerState) {
        val navController = rememberNavController()
        Scaffold(topBar = {
            TopAppBar(title = {
                Text(text = "Demo App")
            },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu Btn" )
                    }
                })
        }) { paddingValues ->
            Column {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    , contentAlignment = Alignment.Center
                ){

                }
            }


        }


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavGrapTheme {
        MainScreen()
    }
}