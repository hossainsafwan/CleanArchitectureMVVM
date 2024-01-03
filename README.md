# Clean Architecture with MVVM

This repository is meant to host a simple Android app that shows a list of countries with their flags, names, and country code, and allows the user to search countries by name.
The purpose of the app is to demonstrate clean architecture with MVVM while not bloating the app with too many features.
The app  uses common libraries such as retrofit and coil-image loading/caching.
There is unit tests in the required sections of the app as well. 

<table><tr><td style="width:50%">
  <video src="https://github.com/hossainsafwan/CleanArchitectureMVVM/assets/22313316/00e8185e-c085-4864-bd3d-c74111cae9ab" controls="controls" style="max-width: 730px;"></video>
  <em>Fig 1: Short video of the simple app</em>
<td style="width:50%">
<img width="580" alt="Screen Shot 2024-01-03 at 3 14 02 AM" src="https://github.com/hossainsafwan/CleanArchitectureMVVM/assets/22313316/7f033d13-1cb5-4d59-9023-a56cf8768a8e">
  
<em>Fig 2: Package structure of the app following Google's Clean Architecture</em>
</td></tr></table>


# Minimal Network Bandwidth Usage

This simple app demonstrates minimal use of network bandwidth despite device configuration changes such as device rotations.

https://github.com/hossainsafwan/CleanArchitectureMVVM/assets/22313316/d55e4025-625e-4a45-af57-e27db2cfdd74

*Fig 3: Network inspection in Android Studio to show minimal bandwidth usage despite device configuration change*


The app also has image caching to minimize the use of network bandwidth using the Coil library.

<img width="1215" alt="Screen Shot 2024-01-03 at 2 45 06 AM" src="https://github.com/hossainsafwan/CleanArchitectureMVVM/assets/22313316/c9dbf0f5-f3de-4958-8bf5-85d5ae516de2">

*Fig 4: Image caching logs*

# Test Coverage

The following are the test coverages of some of the important classes in the app.

<img width="1727" alt="Screen Shot 2024-01-03 at 2 33 32 AM" src="https://github.com/hossainsafwan/CleanArchitectureMVVM/assets/22313316/9b0c2409-82f1-44d9-ad02-f1f7e4b13f10">

*Fig 5: 100% Test Coverage of View Model*

<img width="1727" alt="Screen Shot 2024-01-03 at 2 35 46 AM" src="https://github.com/hossainsafwan/CleanArchitectureMVVM/assets/22313316/c354dc83-f824-44ea-8eaa-4327b331636d">

*Fig 6: 100% Test Coverage of Repository*

<img width="1727" alt="Screen Shot 2024-01-03 at 2 36 36 AM" src="https://github.com/hossainsafwan/CleanArchitectureMVVM/assets/22313316/20faf6a3-ceb0-40e4-ad99-99b49261c492">

*Fig 7: 100% Test Coverage of Use case*


<!--<img width="250" alt="Screen Shot 2023-12-16 at 12 07 17 PM" src="https://github.com/hoss![Uploading Screen Shot 2024-01-03 at 2.36.36 AM.png…]()
ainsafwan/CleanArchitectureMVVM/assets/22313316/c2c8b5ac-ddbe-4bdb-b465-18f05afa5d3c"> -->




