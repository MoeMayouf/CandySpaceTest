# CandySpaceTest

A sample app that shows list of users from the stackexchange api. Utilising ViewModel, coroutines, dagger in a multi modules project in Kotlin by using Clean Code Architecture 

## Communication between layers
1. UI calls method from viewModel in presentation layer
2. ViewModel executes Use case and maps data
3. Use Case calls repository which handles the data store from the data layer 
4. Repository returns data from apiService to then call the Api

## Endpoint used 
Used https://thecatapi.com/v1/ along with some queries

## Improvements
* Modify endpoint to allow queries to be modified by user
* Use string resources and dimensions instead of hard-coding 
* More unit test cases
* create new modules to hold dependencies
* create new module for common/utilities classes
