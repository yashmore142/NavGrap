package com.example.navgrap

import androidx.compose.ui.graphics.vector.ImageVector

data class NavDrawerItem (
    val image : ImageVector,
    val text : String,
    val badgeCount : String,
    val hasBadge : Boolean
)