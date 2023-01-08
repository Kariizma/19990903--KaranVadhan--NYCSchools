# 19990903--KaranVadhan--NYCSchools


### User Stories

- [x] Display a List of NYC High Schools
  - [x] Get the data from here: https://data.cityofnewyork.us/resource/s3k6-pzi2.json
- [x] Selecting a school sould show additional information about the school
  - [x] Display all SAT scores - including Math, Reading, and Writing.
  - [x] Get the data from here: https://data.cityofnewyork.us/resource/f9bf-2cp4.json
  

### App Walkthough GIF

<img src="https://i.imgur.com/nucimIo.gif" width=250><br>


### Notes/Problems

- (BUG) The First 12 items in the RecyclerView are not showing up when you first generate the list, im assuming it has something to do with the Binding and im trying to figure out how to fix it. the items are still usable and are showing the right information just the Text isnt showing.
      - Fixed it, i made a mistake by binding it in Kotlin aswell is binding it in the XML 


## Open-source libraries used
- [Retrofit 2](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java/Kotlin
- [Retrofit Moshi Converter](https://github.com/square/moshi/) Moshi is a modern JSON library for Android, Java and Kotlin. It makes it easy to parse JSON into Java and Kotlin classes
