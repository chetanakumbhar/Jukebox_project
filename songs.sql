create database Jukeboxdb;
use JukeBoxdb;
create table songs(
songId int AUTO_INCREMENT primary key,
song_title varchar(100),
artist varchar(50),
genre varchar(50),
duration varchar(50),
filepath varchar(100)
);

create table playlist(
playlistId int AUTO_INCREMENT primary key,
playlist_name varchar(100)
);

create table songsInPlaylist(
playlistId int, foreign key(playlistId) references playlist(playlistId),
songId int, foreign key(songId) references songs(songId)
);

use jukeboxdb;
insert into songs values(1001,'Bolna-song','Arijit Singh','soul','00:03:32','src/main/resources/songs/Bolna-Gaanabajateyraho.in.wav');
insert into songs(song_title, artist,genre, duration, filepath) values('electronic-rock-king','Pixies','Indie-Rock','00:01:50','src/main/resources/songs/electronic-rock-king-around-here-15045.wav');
insert into songs(song_title, artist,genre, duration, filepath) values('Dilliwaali-Girl-Friend-Yeh-Jawaani-Hai-Deewani','Arijit Singh and Sunidhi Chauhan','Rock','00:04:20','src/main/resources/songs/Dilliwaali-Girl-Friend-Yeh-Jawaani-Hai-Deewani-Gaanabajateyraho.in.wav');
insert into songs(song_title, artist,genre, duration, filepath) values('Dostana-Desi-Girl','Sunidhi Chauhan, Vishal Dadlani','Indian film pop','00:05:06','src/main/resources/songs/electronic-rock-king-around-here-15045.wav');
insert into songs(song_title, artist,genre, duration, filepath) values('Sun-Le-Zara','Arnab Dattla','love','00:04:51','src/main/resources/songs/Sun-Le-Zara-Gaanabajateyraho.in.wav');
insert into songs(song_title, artist,genre, duration, filepath) values('Sooraj-Dooba-Hain','Arijit Singh','Pop‎','00:00:16','Sooraj-Dooba-Hain-Gaanabajateyraho.in.wav');
insert into songs(song_title, artist,genre, duration, filepath) values('Kabira-song','Rekha Bhardwaj ','Indian Film Pop','00:04:30','src/main/resources/songs/Kabira-Gaanabajateyraho.in.wav');
insert into songs(song_title, artist,genre, duration, filepath) values('time-for-something','B B king','silent','00:03:34','time-for-something-new-136901.wav');

use jukeboxdb;
update songs set duration = '00:04:23' where duration = '';
update songs set song_title = 'Time-for-something' where song_title = 'time-for-something';
use jukeboxdb;
insert into songs(songId,song_title, artist,genre, duration, filepath) values(1006,'Money','Lisa','Pop','00:02:50','src/main/resources/songs/Money_192(PaglaSongs).wav');
DELETE FROM songs WHERE song_title = 'Bolna-song' ;
update songs set filepath = 'src/main/resources/songs/Bolna-Gaanabajateyraho.in.wav' where filepath = 'D:/NIIT/capston project/C7S1-P1-Jokebox/src/main/resources/songs/Bolna-Gaanabajateyraho.in.wav';