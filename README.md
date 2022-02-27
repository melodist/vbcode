# VB Code
Chapter 06 - [과제] 압축 프로그래밍

## 구현 내용
- Encoding
  - [x] String Input
  - [x] VB Code Encoding
  - [x] Binary Output
- Decoding
  - [ ] Binary Input
  - [x] VB Code Decoding
  - [ ] String Output
  
## 이슈
- Java byte 범위는 -127 - 128이나 `Integer.parseInt()`는 Unsigned Integer를 기준으로 함
  - Casting으로 해결
  - Casting을 사용할 경우의 단점이나 주의점은 무엇일까?
- Binary File 형식으로 작성할 경우 어디가 `tagName`이고 `EntryId` 인지 구별할 수 없음
  ![vbcode_20220226](https://user-images.githubusercontent.com/52024566/155847225-a5b7f22d-beab-457c-9e5c-d314e7ee1079.png)
  - EntryList: 각 Entry의 길이를 저장한 List로 길이는 Entry 개수 * 4Byte (`int`)
- 일급 컬렉션 사용 방법
  - `usingRecursiveComparison` 사용 
- `Map::keySet()`

## References
- [웹 개발자를 위한 대규모 서비스를 지탱하는 기술](http://www.kyobobook.co.kr/product/detailViewKor.laf?mallGb=KOR&ejkGb=KOR&barcode=9788994506128)
- [Variable byte codes](https://nlp.stanford.edu/IR-book/html/htmledition/variable-byte-codes-1.html)
- [예제 코드 및 테스트 데이터](https://gihyo.jp/book/2010/978-4-7741-4307-1/support)
