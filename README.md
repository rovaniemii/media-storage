# media-storage
카카오 이미지, 동영상 검색 API를 활용해 썸네일 이미지를 검색하고 보관함에 수집하는 안드로이드 앱입니다. 

## Tech stack & Open-source libraries
### Android
- Minimum SDK level 28
- MVVM pattern
- [Kotlin](https://kotlinlang.org/) based
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous.
- JetPack
    - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Create a
      UI that automatically responds to lifecycle events.
    - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Build data
      objects that notify views when the underlying database changes.
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Store UI
      related data that isn't destroyed on app rotations.
    - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Useful to bind
      data directly through layouts xml file, so no `findViewById()` anymore.
    - [RecyclerView](https://developer.android.com/jetpack/androidx/releases/recyclerview) - Display large sets of data in your UI while minimizing memory usage.
        - [DiffUtil](https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil) - DiffUtil is a utility class that calculates the difference between two lists and outputs a list of update operations that converts the first list into the second one.
        - [ListAdapter](https://developer.android.com/reference/androidx/recyclerview/widget/ListAdapter) - base class for presenting List data in a RecyclerView, including computing diffs between Lists on a background thread.
- [Hilt](https://dagger.dev/hilt/) - Dependency injection.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - Construct the REST APIs.
- SharedPreferences

## ◼️ Features
- 검색은 키워드 하나에 이미지 검색과 동영상 검색을 동시에 사용, 두 검색 결과를 합친 리스트를 사용합니다. 
- UI는 fragment 2개를 사용했습니다.
  1. 첫 번째 fragment : 검색 결과
      - 검색어를 입력할 수 있습니다.
      - 검색된 이미지 리스트가 나타납니다. 각 아이템에는 이미지와 함께 날짜와 시간을 표시합니다.
      - 스크롤을 통해 다음 페이지를 불러옵니다.
      - 리스트에서 특정 이미지를 선택하여 '내 보관함'으로 저장할 수 있습니다.
      - 이미 보관된 이미지는 특별한 표시를 보여줍니다. (좋아요/별표/하트 등)
      - 보관된 이미지를 다시 선택하여 보관함에서 제거 가능합니다.
  2. 두 번째 fragment : 내 보관함
      - 검색 결과에서 보관했던 이미지들이 보관한 순서대로 보이도록 했습니다.
      - 보관한 이미지 리스트는 SharedPreferences 사용하여 앱 재시작 후 다시 보이게 구현했습니다.
      
| | | |
|---|---|---|
|<img src="https://user-images.githubusercontent.com/97173983/216802940-23d962e3-4d50-4c1e-b917-c4a9ff8ac909.gif" width="250">|<img src="https://user-images.githubusercontent.com/97173983/216802922-c3023c5d-2eee-4d50-aab4-3eb70b6edb22.gif" width="250">|<img src="https://user-images.githubusercontent.com/97173983/216802953-e6315f59-7715-4b9a-b947-54b2b96a6b80.gif" width="250">|
| | | |
