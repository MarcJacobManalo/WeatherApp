package com.example.weatherapp.login.presentation.revamp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun LoginScreen(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ){
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = "winter horizon",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        Text(
            text = "Simple Login",
            modifier = Modifier
                .fillMaxWidth(),
            color = Color(0xFF369AF8),
            fontSize = 42.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(36.dp))

        var email by rememberSaveable{ mutableStateOf("") }

        TextField(
            value = email,
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(16.dp),
            onValueChange = { text -> email = text },
            placeholder = { Text(text = "Enter your email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = { Icon(painterResource(id = R.drawable.ic_person), contentDescription = "Localized description") },
        )

        var password by rememberSaveable{ mutableStateOf("") }

        TextField(
            value = password,
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(16.dp),
            onValueChange = { text -> password = text },
            placeholder = { Text(text = "Enter your password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = { Icon(painterResource(id = R.drawable.ic_lock), contentDescription = "Localized description") },
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = buttonColors(
                containerColor = Color(0xFF369AF8),
            ),
            shape = RoundedCornerShape(10.dp)) {
            Text(text = "Login")
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Don't have account?")
            Spacer(modifier = Modifier.width(8.dp))
            ClickableText(
                text = AnnotatedString("Sign up"),
                style = TextStyle(color = Color(0xFF369AF8)),
                onClick = {}
            )
        }
    }
}