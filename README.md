# media-storage

## 사용기술
- Minimum SDK level 28
- MVVM pattern
  - Coroutines Flow
  - Retrofit
  - LiveData
  - ViewModel
  - ViewBinding 
    - 현재 구글에서 databinding의 업데이트를 멈추고 compose 를 지원하고 있습니다. 
    - 아직 제가 확실하게 모르는 컴포즈를 적용하는 것 보다, 추 후 컴포즈로 리팩토링을 위한 코드를 작성하는 것이 좋을 것 같아 ViewBinding을 사용했습니다.
  - Hilt
  - RecyclerView
    - DiffUtil
    - ListAdapter
  - SharedPreferences

### 데이터 흐름도
<img width="800" alt="스크린샷 2023-03-25 오후 9 04 49" src="https://user-images.githubusercontent.com/97173983/227716387-1c572fd1-7124-4277-8ccf-e19acc6165de.png">