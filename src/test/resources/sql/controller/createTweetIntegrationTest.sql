DELETE FROM TWEETS;

INSERT INTO TWEETS (account_id, text, image, likes, retweets, replies, views, datetime, location, delete_flag)
VALUES
('user_A','富山のホタルイカ、最高🍻','/src/assets/images/img02.jpg',9,23,7,14,'2024-03-01T15:30:00.000+00:00','Namegawa City, Toyama Prefecture',false),
('user_B','夜間はライトアップも実施「令和6年度 八女黒木大藤まつり」開催！','/src/assets/images/img03.jpg',301,2,0,124,'2024-03-03T11:23:55.000+00:00','Yame City, Fukuoka Prefecture',false);
