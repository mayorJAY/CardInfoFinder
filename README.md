<p align="center">
  <img src="app_iicon.png" title="App Logo">
</p>

# CardInfoFinder

With CardInfoFinder, a user can look up some details of their Credit or Debit Card by simply inputting the Card Number. The App makes use of the API at 
https://binlist.net/. The App was implemented using the MVVM design pattern, Retrofit2, LiveData, ViewModel, Repository pattern, View Binding and Android Recommended 
App Architecture.
# How it Works

When the user types the Card number, the App extracts the BIN/IIN from the number and passes it through the ViewModel, Repository, DataSource to the method that 
makes the API call. If the user mistakenly types a number that is either less or more than 16, he is given a warning (different for each case). In a case where the 
user types a wrong card number, he is shown an error message, also if there is a network problem with the user's device, an error message is displayed.
# Libraries Used

* [Retrofit](https://square.github.io/retrofit/) which is a type-safe REST client for Android which makes it easier to consume RESTful web services
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) used to store and manage UI-related data in a lifecycle conscious way
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) which is an observable data holder class used to handle data in a lifecycle-aware manner
* [View Binding](https://developer.android.com/topic/libraries/view-binding) used to easily write code that interacts with views by referencing them directly
* [Espresso](https://developer.android.com/training/testing/espresso) used to write concise, beautiful, and reliable Android UI tests
* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) used for asynchronous or non-blocking tasks in Kotlin
* [Koin](https://doc.insert-koin.io/#/) a pragmatic lightweight dependency injection framework used in Kotlin
* [Navigation Component](https://developer.android.com/guide/navigation) used to implement navigation and to ensures a consistent and predictable user experience 
while navigating across screens
