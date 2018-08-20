# DDLite Take Home App

The aim of this project is to create an android application that is capable of talking to a repository and using that repository to be able to show the user with a list of information and provide actions related to the information.
Below I will try to explain the architecture used to build the application, the featues implemented and current known limitations of the applications with a plan for improvement in the future.

## Application Architecture

The application follows a layered architecture that is heavily influenced by MVP and Repository design patterns. I made use of Rx Java to make the implementation a bit more easier to do. The following diagram gives a visual overview of the architecture.
![alt text](https://github.com/abhishekdewan101/TakeHomeApp/blob/master/architecture.png)

* The diagram shows the different layers of the architecture used.
* The lowest most layer pertains to the API managers and Local cache and DB implementation used to provide a data store for the app.
* The manager layer is basically a wrapper on top of the lowest layers and provides a clearner way of interfacing with those elements.
* The repository wraps different managers into a single point of contact to be able to provide the presenters with the data they requested. (Note: Future improvement for repository layer would be to integrate the local cache and db saving directly into a base repository thereby removing the need for a LocalDBRepo)
* The presenter layers are to remove the business logic of the app from the views and talk to the repositories to get the information needed by the views.
* The presenters talk to the views and vice versa through contracts that define the scope of information being passed between the two.

## Features Implemented
The following features have been implemented
* Downloading of data from the server and showing the user a list of resturants based on their current location.
* Location is obtained directly from the device.
* Offline caching of the data incase the user looses connection or we are not able to locate the current location of the user. 
* The main home page provides a list of resturants that are closeby. It also provides a easy way to do realtime search on the resturants by using the search bar.
* We also provide the user with a list of filters based on various factors such as price range, delievery fee, delievery time, etc. 
* Also provided to the user is the ability to filter based on tags such as India, Greek, Chineese,etc.
* The resturant details page lists a bunch of useful information to the user including some of the most popular items if available by the resutrant(Please note that not all resturants have popular items and this is being handled by the app)

## Known Limitation
* The app takes a little while to boot up. This is a known limitation and is due to the reasons provided below.
* Obtaining the location from android can sometimes take some time and even fail out right. This is why the app has a timeout mecahnism while getting the location of the app and also tries to get the last known location of the app in case everything else fails. Hence it takes some time.
* Secondly the api's provided for this exercises were found out to not be consistent with the information they provide. I have explained this more in the section "Api Limitations" nonetheless, a decsion was made to go with the heaviest api provided and that takes some time to process.
* The app can also do with some more rohbust error handling. 

## Api Limitations
The provided api were found to have the following limitations:
* The /resturants/id/ api does not provide a list of popular items in its menu object however, when we used /resturants?lat&&long we do get a list of popular menu items in the response. Since we had to implement a resturant detail page I chose to use the first api and ignore the second api.
* The use of the offset param in the /resturant api call leads to weird behavior where the api returns the same resturants multiple times( checked by writing a test program). This was unacceptable as it makes the app look broken, moreover, it messes with the filter functionality I had created. Hence I made a decision to not use the offset params or limit param in the api call and download the entire database as once. This obviously affects boot times however, I was comfortable making that sacrifice.
* The Delivery fee for each resturant is always $0, this messes with the filter by delievery fee filter.

## Improvement Plan
* Improve the boot times by incorporating the offset and limit params back into the api call.
* Writing tests for the entire app, starting with the lower level api classes all the way upto the presenters.
