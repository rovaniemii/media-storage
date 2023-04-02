# media-storage
이미지, 동영상 검색 API를 활용해 이미지를 검색하고 보관함에 수집하는 안드로이드 앱입니다. 

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
      
#### ListAdapter, DiffUtil 사용으로 데이터 불러오는 속도 개선
- RecyclerView를 사용하다보니 하나의 데이터를 변경하는 경우에도 전체 데이터를 리프레쉬 해야하는 불필요한 과정을 겪게 되었습니다. ListAdapter와 DiffUtil을 적용 후 데이터의 equals와 hashcode로 변경이 필요한 코드만 변경할 수 있게 되어 데이터 불러오는 속도를 높일 수 있었습니다.

#### 기술에 맞는 코드 컨벤션을 지키고 사소한 부분도 코드 통일성을 지키기 위해 노력
- MVVM 패턴은 View와 ViewModel는 단방향 통신을 하니 ViewModel에서 get이라는 네이밍을 사용하는 것이 아키텍쳐 스타일과 맞지 않아 그에 맞는 네이밍을 하려 노력했습니다. 또한 build.gradle 파일에 아래의 코드 추가 시 스타일을 ‘ ‘으로 통일하기도 하며 일관된 스타일을 고수하려 했습니다. control+command+l을 습관화 하며 코드 정렬에 힘썼습니다. 이런 노력으로 기술의 이해도를 높이고 더 간단하고 읽기 쉬운 코드를 작성할 수 있게 되었습니다.

#### MVVM 패턴 + Repository 패턴을 적용해 코드 최적화
- MVP의 경우 Presenter는 뷰의 데이터가 변경되면 뷰 변경 요청을 보내는 양방향 통신을 하지만, ViewModel은 뷰의 데이터를 관찰하다 변경되는 변경을 저장할 뿐인 단방향 통신을 하여 뷰와 뷰 모델간 1:n관계를 정의합니다. 1:n관계를 통해 중복되는 코드의 수를 줄여 코드 최적화를 하기위해 노력하였습니다.
