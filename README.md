# Practical-6: Frame-by-Frame Animation & Splash Screen

## Aim

To create an Android application that demonstrates **Frame-by-Frame Animation** and a **Splash Screen with Twin Animation**. The practical also demonstrates immersive mode, edge-to-edge content display, `AnimationDrawable`, Android animation resources, and different animation tags.

## Objectives

1. Create `MainActivity` according to the given UI design.
2. Create `SplashActivity` according to the given animation/video.
3. Implement Frame-by-Frame Animation using `AnimationDrawable`.
4. Implement Twin (View) Animation on the Splash Screen.
5. Create a radial gradient rectangle for the Splash Screen background.
6. Demonstrate scale, translate, rotate, and alpha animations.
7. Use animation resources inside the `res/anim` folder.
8. Demonstrate immersive mode and edge-to-edge content display.
9. Convert SVG resources into Android-compatible XML/vector drawable resources.

## Concepts Covered

- ImageView
- Frame-by-Frame Animation
- Twin Animation
- Splash Screen
- SplashScreen API
- AnimationDrawable
- AnimationUtils
- `loadAnimation()`
- `setAnimationListener()`
- `overridePendingTransition()`
- `finish()`
- `onWindowFocusChanged()`
- Immersive Mode
- Edge-to-Edge Display
- `animation-list`
- `oneShot`
- `set` animation
- `startOffset`
- `duration`
- `scale`
- `translate`
- `rotate`
- `alpha`
- Shape Drawable
- Gradient
- Radial Gradient
- Vector Drawable / SVG conversion
- `res/anim` folder

## 1. Frame-by-Frame Animation

### Definition

**Frame-by-Frame Animation** is an animation technique in which a sequence of different images is displayed one after another at a specified speed. Each image represents one frame of the animation.

For example, a sequence of alarm, heart, or logo images can be displayed continuously to create the appearance of movement.

### AnimationDrawable

Android provides `AnimationDrawable` for frame-by-frame animations.

The frames can be defined using an `animation-list` XML resource.

Example:

```xml
<animation-list
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:oneshot="false">

    <item
        android:drawable="@drawable/frame1"
        android:duration="100" />

    <item
        android:drawable="@drawable/frame2"
        android:duration="100" />

    <item
        android:drawable="@drawable/frame3"
        android:duration="100" />

</animation-list>
```

The animation can be assigned to an `ImageView`:

```kotlin
imageView.setBackgroundResource(R.drawable.frame_animation)

val animation = imageView.background as AnimationDrawable
animation.start()
```

## 2. Twin Animation

### Definition

**Twin Animation**, also called **View Animation**, is an animation technique in which properties of a View are changed over a period of time.

Android provides four common types:

- Scale Animation
- Translate Animation
- Rotate Animation
- Alpha Animation

These animations can be combined using the `<set>` tag.

## 3. Scale Animation

The `<scale>` tag changes the size of a View during animation.

Example:

```xml
<scale
    android:fromXScale="0.0"
    android:toXScale="1.0"
    android:fromYScale="0.0"
    android:toYScale="1.0"
    android:duration="1000" />
```

## 4. Translate Animation

The `<translate>` tag moves a View from one position to another.

Example:

```xml
<translate
    android:fromXDelta="0"
    android:toXDelta="0"
    android:fromYDelta="100"
    android:toYDelta="0"
    android:duration="1000" />
```

## 5. Rotate Animation

The `<rotate>` tag rotates a View around a pivot point.

Example:

```xml
<rotate
    android:fromDegrees="0"
    android:toDegrees="360"
    android:duration="1000"
    android:pivotX="50%"
    android:pivotY="50%" />
```

## 6. Alpha Animation

The `<alpha>` tag changes the transparency of a View.

Example:

```xml
<alpha
    android:fromAlpha="0.0"
    android:toAlpha="1.0"
    android:duration="1000" />
```

## 7. Combining Animations using `<set>`

Multiple animations can be combined using the `<set>` tag.

Example:

```xml
<set xmlns:android="http://schemas.android.com/apk/res/android">

    <scale
        android:fromXScale="0.0"
        android:toXScale="1.0"
        android:fromYScale="0.0"
        android:toYScale="1.0"
        android:duration="1000" />

    <rotate
        android:fromDegrees="0"
        android:toDegrees="360"
        android:duration="1000" />

    <alpha
        android:fromAlpha="0.0"
        android:toAlpha="1.0"
        android:duration="1000" />

</set>
```

## 8. Animation Timing

Two important XML attributes are:

### `android:startOffset`

Specifies the delay before an animation begins.

```xml
android:startOffset="100"
```

This delays the animation by 100 milliseconds.

### `android:duration`

Specifies how long the animation runs.

```xml
android:duration="1000"
```

This makes the animation run for 1000 milliseconds (1 second).

## 9. AnimationUtils

`AnimationUtils` is used to load animation resources from the `res/anim` folder.

Example:

```kotlin
val animation = AnimationUtils.loadAnimation(
    this,
    R.anim.splash_animation
)
```

The animation can then be applied to a View:

```kotlin
imageView.startAnimation(animation)
```

## 10. Animation Listener

An animation listener can be used to perform an action when an animation starts, repeats, or ends.

Example:

```kotlin
animation.setAnimationListener(object :
    Animation.AnimationListener {

    override fun onAnimationStart(animation: Animation) {
    }

    override fun onAnimationEnd(animation: Animation) {
        // Open MainActivity
    }

    override fun onAnimationRepeat(animation: Animation) {
    }
})
```

## 11. SplashActivity

`SplashActivity` is displayed when the application starts.

The Splash Screen can contain:

- University/application logo
- Gradient background
- Twin animation
- Scale animation
- Rotate animation
- Alpha animation
- Translate animation

After the animation finishes, `MainActivity` is opened.

Example:

```kotlin
val intent = Intent(this, MainActivity::class.java)
startActivity(intent)
finish()
```

## 12. Splash Screen Gradient Background

A gradient rectangle can be created using the `<shape>` and `<gradient>` tags.

Example:

```xml
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">

    <gradient
        android:type="radial"
        android:centerX="0.9"
        android:centerY="0.9"
        android:gradientRadius="1500"
        android:startColor="#FFC0CB"
        android:endColor="#0000FF" />

</shape>
```

The required properties are:

- Shape: Rectangle
- Gradient type: Radial
- X: `0.9`
- Y: `0.9`
- Radius: `1500`
- Start Color: Pink
- End Color: Blue

## 13. Splash Screen API

Modern Android applications can use the Android SplashScreen API.

The Splash Screen is displayed while the application is starting and can be configured through the application's theme and splash-screen configuration.

The practical also demonstrates a custom `SplashActivity` for applying the required animation sequence.

## 14. Immersive Mode

**Immersive Mode** allows an application to hide system bars so that content can use more of the screen.

It is useful for applications where a full-screen experience is required, such as animations, games, and media applications.

The modern Android approach uses `WindowInsetsController` / `WindowInsetsControllerCompat` to control system bars.

## 15. Edge-to-Edge Content Display

**Edge-to-edge** allows application content to extend behind the system bars and use the full available display area.

This can be enabled using:

```kotlin
WindowCompat.setDecorFitsSystemWindows(window, false)
```

When using edge-to-edge content, padding or insets may need to be applied so important UI elements are not hidden behind system bars.

## 16. onWindowFocusChanged()

`onWindowFocusChanged()` is a lifecycle-related Activity method that is called when the Activity gains or loses window focus.

Example:

```kotlin
override fun onWindowFocusChanged(hasFocus: Boolean) {
    super.onWindowFocusChanged(hasFocus)

    if (hasFocus) {
        // Start required animation or immersive UI
    }
}
```

It can be useful for applying full-screen/immersive behavior when the Activity becomes visible and gains focus.

## 17. overridePendingTransition()

`overridePendingTransition()` can be used to specify enter and exit animations when transitioning between Activities.

Example:

```kotlin
startActivity(Intent(this, MainActivity::class.java))
overridePendingTransition(
    R.anim.fade_in,
    R.anim.fade_out
)
```

The exact API behavior varies with Android version, and newer Android versions provide newer transition APIs.

## 18. finish()

`finish()` closes the current Activity.

In a Splash Activity, it prevents the user from returning to the splash screen after opening the Main Activity.

Example:

```kotlin
startActivity(Intent(this, MainActivity::class.java))
finish()
```

## 19. Animation Resource Folder

Animation XML files should be stored in:

```text
app/src/main/res/anim/
```

Example:

```text
res/
 ├── anim/
 │    ├── scale.xml
 │    ├── translate.xml
 │    ├── rotate.xml
 │    ├── alpha.xml
 │    └── splash_animation.xml
 ├── drawable/
 │    ├── frame1.xml
 │    ├── frame2.xml
 │    └── splash_background.xml
 └── layout/
      ├── activity_main.xml
      └── activity_splash.xml
```

## 20. Converting SVG to XML

SVG images can be imported into Android Studio as Vector Drawable XML files.

The resulting file can be stored in:

```text
res/drawable/
```

and referenced as:

```xml
android:src="@drawable/uvpce_logo"
```

Vector Drawables are useful for logos and icons because they can scale to different screen sizes without losing quality.

## 21. ImageView

`ImageView` is used to display images and can also be used as the target View for animations.

Example:

```xml
<ImageView
    android:id="@+id/imageView"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:src="@drawable/uvpce_logo" />
```

## 22. Required Animation Resources

The practical requires demonstration of the following XML elements and attributes:

| Element / Attribute | Purpose |
|---|---|
| `<animation-list>` | Defines frame-by-frame animation |
| `oneShot` / `oneshot` | Controls whether frame animation runs once |
| `<set>` | Combines multiple animations |
| `startOffset` | Delays animation start |
| `duration` | Specifies animation duration |
| `<scale>` | Changes View size |
| `<translate>` | Moves View |
| `<rotate>` | Rotates View |
| `<alpha>` | Changes transparency |

## 23. Application Flow

```text
                    Application Start
                           |
                           v
                    SplashActivity
                           |
                           v
                 Gradient Background
                           |
                           v
                    Logo / ImageView
                           |
                           v
                  Twin Animation
               +-----------+-----------+
               |           |           |
             Scale      Rotate       Alpha
               |           |           |
               +-----------+-----------+
                           |
                      Animation End
                           |
                           v
                     MainActivity
                           |
                           v
               Frame-by-Frame Animation
                           |
                           v
                  AnimationDrawable
```

## 24. Animation Sequence Resources

The practical provides image sequences for:

- Alarm
- Heart
- UVPCE Logo

These images can be added to the project's drawable resources and used to create frame-by-frame animations.

## 25. Expected Output

### Splash Screen

- Full-screen/edge-to-edge splash interface.
- Radial pink-to-blue gradient background.
- UVPCE/application logo.
- Twin animation using scale, translate, rotate, and alpha effects.
- Transition from SplashActivity to MainActivity.

### Main Activity

- UI according to the provided design.
- ImageView displaying the selected image sequence.
- Frame-by-frame animation using `AnimationDrawable`.

## 26. Learning Outcomes

After completing this practical, the student will be able to:

- Understand Frame-by-Frame Animation.
- Understand Twin/View Animation.
- Create animations using XML resources.
- Use `AnimationDrawable`.
- Use `AnimationUtils.loadAnimation()`.
- Use animation listeners.
- Combine animations using `<set>`.
- Implement scale, translate, rotate, and alpha animations.
- Control animation timing using `startOffset` and `duration`.
- Create and configure a Splash Screen.
- Apply gradient backgrounds using XML drawables.
- Understand immersive mode.
- Implement edge-to-edge content display.
- Use `onWindowFocusChanged()`.
- Understand Activity transitions.
- Use `finish()` after SplashActivity.
- Convert/import SVG assets as Android Vector Drawables.
- Organize animation resources in the `res/anim` folder.

## Conclusion

Practical-6 demonstrates Android animation techniques by implementing **Frame-by-Frame Animation** and **Twin Animation**. The practical also covers Splash Screens, gradient backgrounds, animation resources, Activity transitions, immersive mode, edge-to-edge display, and vector drawable resources. These concepts help in creating visually appealing and interactive Android applications.
