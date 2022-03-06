package com.benshapiro.cst

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Annotate application class and tell Hilt to
// start the code generation
@HiltAndroidApp
class CSTApp : Application() {
}