![](https://img.shields.io/github/issues/Draylar/jsin?style=for-the-badge)
![](https://img.shields.io/github/issues-pr/Draylar/jsin?style=for-the-badge)
![](https://img.shields.io/github/v/release/Draylar/jsin?style=for-the-badge)

JSIN (otherwise known as JavaScript Object Notation Image Notation, or JSON Image Notation) is a simple image format designed around JSON. 

Here is a simple .jsin file that contains a single blue pixel:
```json
{
  "width": 1,
  "height": 1,
  "pixels": [
    [
      {
        "x": 1,
        "y": 1,
        "color": {
          "r": 0.0,
          "g": 0.0,
          "b": 1.0,
          "a": 1.0
        }
      }
    ]
  ]
}
```

## Usage
JSIN is easy to use, whether you interact with the CLI app as a user or API as a developer.

#### For Users
*Convert a .jsin file to another image file type:*

`jsin to png apple.jsin`


*Convert a non-.jsin image file to .jsin:*

`jsin from apple.png`

#### For Developers
*Coming soon...*

## Why use JSIN?
- []()

## Credits:

*Fall Autumn Red Season Woods* Image in test resources by <a href="https://pixabay.com/users/valiphotos-1720744/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=image&amp;utm_content=1072821">Valiphotos</a> from <a href="https://pixabay.com/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=image&amp;utm_content=1072821">Pixabay</a>
