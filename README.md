# media-storage
카카오 이미지, 동영상 검색 API를 활용해 썸네일 이미지를 검색하고 보관함에 수집하는 안드로이드 앱입니다.

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

## ◼️ Architecture $ Modularization
- Clean Architecture를 참고하여 멀티모듈 앱을 설계 했습니다.
- 관심사 분리(UI, Domain, Data)를 통해 코드의 복잡성을 줄일 수 있었고, Hilt를 통한 의존성 주입 구현을 통해 클래스간 의존 관계를 분리해ㅐ 유지보수가 쉬워졌습니다.
- 저수준 모듈인 UI와 Data가 고수준 모듈(추상적 모듈)인 Domain 에 의존하도록 구현해서 고수준 모듈이 저수준 모듈에 영행을 받지 않게 해 유지보수 및 확장에 용이하도록 설계했습니다.

<p align='center'>
<img width='600' src='https://github.com/hy0417sage/media-storage/assets/97173983/786952ac-6e64-4ec9-89a9-50203a99a998'>
</p>

- MVVM 아키텍처와 Repository 패턴을 베이스로 하여 구성했습니다.

<p align='center'>
<img width='600' src='https://user-images.githubusercontent.com/97173983/216803295-5ea485be-d9ff-429d-ac31-3b1d4e2e646f.png'>
</p>

## Description of each module
- app : DI, Application, UI 클래스와 레이아웃을 모아놓은 모듈입니다.
- core : 각 모듈에서 공통으로 필요한 클래스와 리소스를 모아놓은 모듈입니다.
- data : 로컬로부터 데이터를 받아 가공하는 비즈니스 로직이 포함되며, 가공 후 도메인 계층에
- domain : Data 모듈과 UI 모듈의 의존성을 분리하고, 저수준 모듈(UI, Data)의 추상화 모듈인 고수준 모듈입니다.


## ◼️ Tech stack & Open-source libraries
### Android
- Minimum SDK level 26
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