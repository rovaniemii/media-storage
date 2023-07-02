# media-storage
카카오 이미지, 동영상 검색 API를 활용해 썸네일 이미지를 검색하고 보관함에 수집하는 안드로이드 앱입니다.
~~~
이미지 검색 API
https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-image

동영상 검색 API
https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-video
~~~

## ◼️ Features
- 검색은 키워드 하나에 이미지 검색과 동영상 검색을 동시에 사용, 두 검색 결과를 합친 리스트를 사용합니다. 
  - **검색어를 입력하고 검색 버튼을 누르면 이미지 검색 API와 동영상 검색 API를 합쳐 최신순으로 첫 페이지를 호출합니다.**
    - 이미지, 동영상 2가지 API 데이터를 합쳐 최신부터 나타나도록 정렬한 후 검색 탭에 바로 보여줄 수 있도록 구현
    - 사용자에게 보여주는 **데이터의 확실성(최신순으로 리스트 출력)**을 고려하기 위해 유저가 앱을 사용하는 동안 백그라운드에서 호출할 수 있는 모든 데이터를 호출, 전체 리스트를 갱신해 보여주고, 스크롤을 통해 다음 페이지를 호출할 수 있습니다.
- **검색된 이미지를 클릭하면, 해당 이미지가 내 보관함에 저장됩니다.**
  - 다시 검색하더라도 내 보관함에 있는 이미지라면 하트 표시의 북마크가 유지됩니다.
  - Navigation의 보관함 탭을 누르면 앞서 보관한 이미지들이 저장한 순서대로 보이고, 앱 재 시작 후에도 다시 확인할 수 있습니다.
  - 보관한 이미지를 누르면 이미지가 사라짐과 동시에 검색 탭의 이미지에 하트 표시가 사라집니다.


## ◼️ Architecture & Modularization
- Clean Architecture를 참고하여 멀티모듈 앱을 설계 했습니다.
- 관심사 분리(UI, Domain, Data)를 통해 코드의 복잡성을 줄일 수 있었고, Hilt를 통한 의존성 주입 구현을 통해 클래스간 의존 관계를 분리해 유지보수가 쉬워졌습니다.
- 저수준 모듈인 UI와 Data가 고수준 모듈(추상적 모듈)인 Domain 에 의존하도록 구현해서 고수준 모듈이 저수준 모듈에 영행을 받지 않게 해 유지보수 및 확장에 용이하도록 설계했습니다.

<p align='center'>
<img width='450' src='https://github.com/hy0417sage/media-storage/assets/97173983/786952ac-6e64-4ec9-89a9-50203a99a998'>
</p>

- MVVM 아키텍처와 Repository 패턴을 베이스로 하여 구성했습니다.

<p align='center'>
<img width='450' src='https://user-images.githubusercontent.com/97173983/216803295-5ea485be-d9ff-429d-ac31-3b1d4e2e646f.png'>
</p>


## Description of each module
- app : DI, Application, UI 클래스와 레이아웃을 모아놓은 모듈입니다.
- core : 각 모듈에서 공통으로 필요한 클래스와 리소스를 모아놓은 모듈입니다.
- data : 서버로 부터 데이터를 받아 가공하는 비즈니스 로직이 포함됩니다.
- domain : Data 모듈과 UI 모듈의 의존성을 분리하고, 저수준 모듈(UI, Data)의 추상화 모듈인 고수준 모듈입니다.


## ◼️ Tech stack & Open-source libraries
### Android
- Minimum SDK level 26
- MVVM pattern

### Skill Set
| 구분 | Skill |
|:---|:---|
| Language | Kotlin |
| Networking | Retrofit, Okhttp, Moshi |
| Asynchronous | Coroutine, Flow |
| DI | Hilt |
| ETC |ViewBinding, Glide, Paging3, DiffUtil |
