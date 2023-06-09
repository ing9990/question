use questiondb;

insert into user(user_id, created_time, modified_time, login_email, login_password, profile_image_url, user_status,
                 username)
values ('userid1', now(), now(), 'test@test.com', 'password1', 'image1', 'ACTIVE', 'testuser1');

insert into user(user_id, created_time, modified_time, login_email, login_password, profile_image_url, user_status,
                 username)
values ('userid2', now(), now(), 'test2@test.com', 'password2', 'image2', 'ACTIVE', 'testuser2');

insert into user(user_id, created_time, modified_time, login_email, login_password, profile_image_url, user_status,
                 username)
values ('userid3', now(), now(), 'test3@test.com', 'password3', 'image3', 'ACTIVE', 'testuser3');

insert into user(user_id, created_time, modified_time, login_email, login_password, profile_image_url, user_status,
                 username)
values ('userid4', now(), now(), 'mail80782258@gmail.com',
        '$2a$10$omBpft4efLF7SYaQ7XHuruBcw8KDUlWcsu3fD7ndKsfBUIv22JxVS',
        'image4', 'ACTIVE', '김태우');

insert into question(question_id, created_time, modified_time, question_detail, question_title, question_author)
values (null, now(), now(), 'TCP와 UDP의 차이를 알려주세요!!', 'TCP와 UDP의 차이', 'userid1');

insert into question(question_id, created_time, modified_time, question_detail, question_title, question_author)
values (null, now(), now(), '알고리즘을 푸는데 BFS가 뭔지 모르겠어요 ㅠㅠ', 'BFS 알고리즘이 뭔가요?', 'userid2');

insert into question(question_id, created_time, modified_time, question_detail, question_title, question_author)
values (null, now(), now(), '소프트웨어 디자인 패턴이라는데 뭔지 자세히 알려주세요.', '파사드 패턴이 뭔가요?', 'userid3');

insert into answer(answer_id, created_time, modified_time, answer_content, answer_title, answerer_id, question_id)
values (null, now(), now(), '그래프를 탐색하는 알고리즘 중 하나로 주로 큐를 이용해서 구현합니다. 최단경로 알고리즘에 많이 쓰입니다.', 'BFS에 대해 알려드릴게요',
        'userid1', 2);

insert into answer(answer_id, created_time, modified_time, answer_content, answer_title, answerer_id, question_id)
values (null, now(), now(), 'TCP는 연결 지향적이고 신뢰성이 있습니다. 하지만 UDP는 신뢰성이 낮고 작은 데이터를 여러번 보내는데 적합합니다.', '네트워크에 대해 답변 드립니다.',
        'userid2', 2);

insert into answer(answer_id, created_time, modified_time, answer_content, answer_title, answerer_id, question_id)
values (null, now(), now(), '파사드패턴이란 방대한 프레임워크 코드를 내 외부에 쉽게 사용할 수 있도록 만드는 디자인 패턴이에요. 주로 VideoCOnverter를 예시로 많이 사용해요',
        '파사드 패턴에 대해 설명', 'userid3', 2);

insert into watchlist(watchlist_id, created_time, modified_time, watchlist_description, watchlist_name,
                      watchlist_creator_id)
values (null, now(), now(), 'CS 지식에 관심목록입니다.', '유용한 CS 지식 모음', 'userid1');


insert into watchlist(watchlist_id, created_time, modified_time, watchlist_description, watchlist_name,
                      watchlist_creator_id)
values (null, now(), now(), '취미에 대한 관심목록입니다.', '김땡떙의 취미 모음', 'userid2');


insert into watchlist(watchlist_id, created_time, modified_time, watchlist_description, watchlist_name,
                      watchlist_creator_id)
values (null, now(), now(), '게임에 대한 관심목록입니다.', '게임 관심목록', 'userid3');

insert into watchlist(watchlist_id, created_time, modified_time, watchlist_description, watchlist_name,
                      watchlist_creator_id)
values (null, now(), now(), '테스트용 관심목록입니다.', '테스트 관심목록', 'userid4');

insert into watchlist(watchlist_id, created_time, modified_time, watchlist_description, watchlist_name,
                      watchlist_creator_id)
values (null, now(), now(), '테스트용 관심목록입니다.2', '테스트 관심목록2', 'userid4');

insert into watchlist_contents(watchlist_id, question_id)
values (1, 1);

insert into watchlist_contents(watchlist_id, question_id)
values (2, 2);

insert into watchlist_contents(watchlist_id, question_id)
values (3, 3);

insert into watchlist_contents(watchlist_id, question_id)
values (4, 1);

insert into watchlist_contents(watchlist_id, question_id)
values (4, 3);

insert into watchlist_contents(watchlist_id, question_id)
values (5, 1);

insert into watchlist_contents(watchlist_id, question_id)
values (5, 2);

insert into watchlist_contents(watchlist_id, question_id)
values (5, 3);