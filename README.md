## 기능 요구 사항

- 체스판
    - [X] 가로는 왼쪽부터 a ~ h로 나타낸다.
    - [X] 세로는 아래부터 위로 1 ~ 8로 나타낸다.
    - [X] 게임 시작시, 각 좌표에 체스 말(기물)을 배치한다.
    - [x] 각 진영의 점수를 계산한다.

- 체스 말
    - [x] 각 진영은 대, 소문자로 구분한다.
        - 검은색 : 대문자
        - 흰색 : 소문자
    - [x] 각 기물의 이동 규칙에 따라 이동한다.
    - [x] 이동하고자 하는 위치에
        - 자신의 기물이 있을 경우, 이동할 수 없다.
        - 상대방의 기물이 있을 경우, 해당 기물을 잡을 수 있다.
    - [ ] 폰(Pawn)이 상대방의 마지막 rank에 도착하면, 폰과 킹을 제외한 기물로 승격할 수 있다.
    - [x] 킹이 잡힌 경우 게임이 종료된다.
    - [x] 킹을 제외한 기물은 점수가 존재한다
        - [x] 퀸 9 룩 5점 비숍 3점 나이트 2.5점
        - [x] 폰은 기본적으로 1점 하지만 같은 가로줄에 자신의 폰이 2개 이상 있을 경우 0.5점으로 계산한다.


- 게임 진행
    - [ ]  방 이름을 입력받는데
      - [ ] 이미 있는 이름일 경우(게임 기록이 있을 경우) 재시작을 물어본다.
        - [ ] 'resume' 입력시 게임 재게
        - [ ] 'restart' 입려시 새게임 생성
    - [x] `start` 입력시, 게임을 시작한다.
    - [x] `end` 입력시, 게임을 종료한다.
    - [x] `move source위치 target위치` 입력시, source 위치에 있는 기물이 target 위치로 이동한다.
    - [x] 'status' 입력시 점수와 승리 중인 진형을 출력한다
    - [x] 킹이 잡힌 경우 점수 없이 게임이 종료된다.

