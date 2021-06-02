# LunarDay from Javascript

## Usage

- include `lunardayjs` into `build.gradle`

```java

// need a constructor parameter, it's a invisible webView.
new LunarJS(webView).lunarDays(
        date,
        50,
        30,
        new LunarJS.LunarDayCallback() {
            public void onResultReceived(List<LunarDay> results) {
                viewModel.lunarDay.setValue(results);
                System.out.println(results);
            }
        }
);
```

To use it is pretty straighforward. Initiate the `LunarJS` constructor using
an invisible webView. Then call the `lunarDays` method.

Parameters:
- `date`. It's either date string with the `yyyy-MM-dd` format, or a `Date` object
- `latitude` integer
- `longitude` integer
- callback, `LunarJS.LunarDayCallback` object. Nullable.


### How to change the javascript file

the javascript file is under folder `lunardayjs/src/main/assets/`. What I do
is with version from github is:
- make the `lunarDays` function visible through `window`
- compile it using `browserify`
- create result converter, to make the parsing process easy.
- copy the files into that folder


### ScreenShoot

![Screenshot](screenshot.png?raw=true)