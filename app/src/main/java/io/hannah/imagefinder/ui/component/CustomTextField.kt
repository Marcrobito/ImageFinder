package io.hannah.imagefinder.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    isLoading: Boolean = false,
    isEnabled: Boolean = true,
    hint: String? = null,
    value: String,
    vectorIcon: ImageVector? = null,
    onValueChange: (String) -> Unit,
) {

    val interactionSource = remember { MutableInteractionSource() }

    val textFieldDefaults =
        TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary
        )

    val borderWidth = remember {
        mutableStateOf(1)
    }

    OutlinedTextField(
        value = value,
        enabled = isEnabled,
        onValueChange = {
            borderWidth.value = 2
            onValueChange(it)
        },
        shape = RoundedCornerShape(18.dp),
        placeholder = {
            if (hint != null) {
                Text(
                    hint,
                    color = Color.Gray.copy(alpha = 0.6F)
                )
            }
        },
        colors = textFieldDefaults,
        modifier = Modifier
            .border(
                width = borderWidth.value.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(18.dp)
            )
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(18.dp)
            ),
        trailingIcon = {
            if (!isLoading) {
                vectorIcon?.let { icon ->
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )

                }
            } else {
                CircularProgressIndicator(
                    modifier = Modifier.height(22.dp).width(22.dp),
                    )
            }
        },
        interactionSource = interactionSource
    )
}

@Composable
@Preview
fun CustomTextFieldPreview() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
    ) {
        CustomTextField(
            value = "",
            hint = "123 probando...",
            onValueChange = {},
            vectorIcon = Icons.Outlined.Search
        )
    }

}