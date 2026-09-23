// SplashScreen.kt
// Place in: app/src/main/java/com/kareem/littlelemon/ui/splash/LemonSplashScreen.kt

package com.kareem.littlelemon.ui.splash   // ← change to your package name

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val LemonYellow   = Color(0xFFF5C518)
private val LemonShadow   = Color(0xFFD4A800)
private val LeafGreen     = Color(0xFF3D5A4C)
private val LeafDarkGreen = Color(0xFF2A3E34)

@Composable
fun LemonSplashScreen(
    onSplashComplete: () -> Unit = {}
) {
    val lemonScale    = remember { Animatable(0f) }
    val shineAlpha    = remember { Animatable(0f) }
    val leaf1Rotation = remember { Animatable(0f) }
    val leaf2Rotation = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        // Lemon pops in
        lemonScale.animateTo(
            1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessMedium
            )
        )

        // Shine fades in
        shineAlpha.animateTo(0.55f, animationSpec = tween(400))

        // Leaves wave forever
        launch {
            leaf1Rotation.animateTo(
                12f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1200, easing = FastOutSlowInEasing),
                    repeatMode = RepeatMode.Reverse
                )
            )
        }
        launch {
            delay(200)
            leaf2Rotation.animateTo(
                -10f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1400, easing = FastOutSlowInEasing),
                    repeatMode = RepeatMode.Reverse
                )
            )
        }

        // Navigate away after 2 seconds
        delay(2000L)
        onSplashComplete()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(200.dp)) {
            val w  = size.width
            val h  = size.height
            val cx = w * 0.5f
            val cy = h * 0.5f

            scale(
                scaleX = lemonScale.value,
                scaleY = lemonScale.value,
                pivot  = Offset(cx, cy)
            ) {

                // Shadow
                drawOval(
                    color   = LemonShadow.copy(alpha = 0.35f),
                    topLeft = Offset(cx - w * 0.28f, cy + h * 0.18f),
                    size    = Size(w * 0.56f, h * 0.08f)
                )

                // Main body
                drawOval(
                    color   = LemonYellow,
                    topLeft = Offset(cx - w * 0.30f, cy - h * 0.22f),
                    size    = Size(w * 0.60f, h * 0.44f)
                )

                // Left bump
                drawOval(
                    color   = LemonYellow,
                    topLeft = Offset(cx - w * 0.40f, cy - h * 0.16f),
                    size    = Size(w * 0.18f, h * 0.28f)
                )

                // Right tip
                drawOval(
                    color   = LemonYellow,
                    topLeft = Offset(cx + w * 0.25f, cy - h * 0.18f),
                    size    = Size(w * 0.16f, h * 0.20f)
                )

                // Shine 1
                rotate(-20f, pivot = Offset(cx - w * 0.12f, cy - h * 0.16f)) {
                    drawOval(
                        color   = Color.White.copy(alpha = shineAlpha.value),
                        topLeft = Offset(cx - w * 0.21f, cy - h * 0.21f),
                        size    = Size(w * 0.18f, h * 0.10f)
                    )
                }

                // Shine 2
                rotate(-20f, pivot = Offset(cx - w * 0.05f, cy - h * 0.10f)) {
                    drawOval(
                        color   = Color.White.copy(alpha = shineAlpha.value * 0.7f),
                        topLeft = Offset(cx - w * 0.10f, cy - h * 0.14f),
                        size    = Size(w * 0.08f, h * 0.06f)
                    )
                }

                // Stem
                val stemPath = Path().apply {
                    moveTo(cx, cy - h * 0.22f)
                    cubicTo(cx + w * 0.01f, cy - h * 0.30f, cx + w * 0.01f, cy - h * 0.35f, cx + w * 0.02f, cy - h * 0.39f)
                }
                drawPath(stemPath, color = LeafGreen, style = Stroke(width = w * 0.013f, cap = StrokeCap.Round))

                // Leaf 1 (right, larger)
                rotate(leaf1Rotation.value, pivot = Offset(cx + w * 0.08f, cy - h * 0.32f)) {
                    val leaf1 = Path().apply {
                        moveTo(cx + w * 0.02f, cy - h * 0.39f)
                        cubicTo(cx + w * 0.10f, cy - h * 0.50f, cx + w * 0.26f, cy - h * 0.52f, cx + w * 0.28f, cy - h * 0.43f)
                        cubicTo(cx + w * 0.30f, cy - h * 0.34f, cx + w * 0.12f, cy - h * 0.29f, cx + w * 0.02f, cy - h * 0.39f)
                        close()
                    }
                    drawPath(leaf1, color = LeafGreen)
                    val vein1 = Path().apply {
                        moveTo(cx + w * 0.02f, cy - h * 0.39f)
                        lineTo(cx + w * 0.26f, cy - h * 0.45f)
                    }
                    drawPath(vein1, color = LeafDarkGreen.copy(alpha = 0.5f), style = Stroke(width = w * 0.007f))
                }

                // Leaf 2 (left, smaller)
                rotate(leaf2Rotation.value, pivot = Offset(cx - w * 0.05f, cy - h * 0.32f)) {
                    val leaf2 = Path().apply {
                        moveTo(cx + w * 0.01f, cy - h * 0.38f)
                        cubicTo(cx - w * 0.06f, cy - h * 0.50f, cx - w * 0.20f, cy - h * 0.51f, cx - w * 0.22f, cy - h * 0.43f)
                        cubicTo(cx - w * 0.24f, cy - h * 0.35f, cx - w * 0.08f, cy - h * 0.30f, cx + w * 0.01f, cy - h * 0.38f)
                        close()
                    }
                    drawPath(leaf2, color = LeafGreen)
                }
            }
        }
    }
}