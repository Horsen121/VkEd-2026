package com.myproject.vked2026

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.myproject.vked2026.ui.theme.VkEd2026Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VkEd2026Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        val textFieldState = rememberTextFieldState("", TextRange(0))
        TextField(
            state = textFieldState
        )
        Spacer(Modifier.height(16.dp))

        TextToSecondActivity(
            context = context,
            text = textFieldState.text.ifEmpty { "Empty string" } as String
        )
        Spacer(Modifier.height(16.dp))

        CallToFriend(
            context = context,
            number = textFieldState.text.toString()
        )
        Spacer(Modifier.height(16.dp))

        SendText(
            context = context,
            text = textFieldState.text.ifEmpty { "Empty string" } as String
        )
    }
}

@Composable
private fun TextToSecondActivity(
    context: Context,
    text: String,
    modifier: Modifier = Modifier
) {
    val intent = Intent(
        context,
        SecondActivity::class.java
    ).apply {
        addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        putExtra("TextField", text)
    }
    Button(
        onClick = { context.startActivity(intent) }
    ) {
        Text(
            text = "Go to second Activity"
        )
    }
}

@Composable
private fun CallToFriend(
    context: Context,
    number: String,
    modifier: Modifier = Modifier
) {
    val isNumberValid = PhoneNumberUtils.isGlobalPhoneNumber(number)

    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.fromParts("tel", number, null)
    }
    Button(
        onClick = {
            if(isNumberValid)
                context.startActivity(intent)
            else
                Toast.makeText(context, "Number is invalid", Toast.LENGTH_LONG).show()
        }
    ) {
        Text(
            text = "CallToFriend"
        )
    }
}

@Composable
private fun SendText(
    context: Context,
    text: String,
    modifier: Modifier = Modifier
) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }
    Button(
        onClick = {
                context.startActivity(intent)
            }
    ) {
        Text(
            text = "Send a message"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VkEd2026Theme {
        MainScreen()
    }
}