# LOPH
## 2019.6.11 by ZyGan1999
### 添加了新的分支
- MusicPlayer 用于实现歌曲的播放
- [x] [任务]改写MusicListActivity传递参数的方式，并在GameActivity中启动音乐播放
## 2019.6.6 by ZyGan1999
### 新的分支设计
<b>之前由于功能设计不清楚造成了分支功能的杂糅，现在已经重新开启了5个分支，每个分支的任务如下</b>
#### MusicList
- [x] [涉及文件]MusicListActivity.java;activity_music_list.xml;GameActivity.java;MainActivity.java;
- [x] [任务]编写这个activity的layout，传递参数，建立MainActivity-MusicListActivity-GameActivity的连接，详情见注释
- [x] [注意事项]**GameActivity和MainActivity中只改intent切换界面部分，不要动其他的代码**
#### Timer
- [x] [涉及文件]TimeCountDown.java;GameActivity.java;KeyPress.java
- [x] [任务]编写计时器的函数和方法
#### CountScore
- [x] [涉及文件]GameActivity.java
- [x] [任务]编写GameActivity的成员函数，用于记分；编写button们的事件监听器
#### Styles
- [ ] [涉及文件]所有需要样式设计的activity;Resources文件夹
- [ ] [任务]编写设计的样式并应用到控件上
#### ResultActivity
- [x] [涉及文件]ResultActivity.java;GameActivity.java;
- [x] [任务]编写结算界面，从GameActivity中传递分数参数，建立intent连接
### 注意事项【重要】
- 除了写明的[涉及文件]之外，不要越俎代庖，如果有必要更改其他的文件，请与大家讨nun（扎心摸仙语气）
- 不要在GameActivity中加测试代码，至少不要提交上去，删干净，之后再一起测试功能
- 不要两个人同时写一个分支，一个人开始写了之后在群里告知一下
- 写完了之后联系pull request & merge
## 2019.6.5 by ZyGan1999
### 当前应当解决的问题
#### 界面的扩充和UI的美化
- [ ] 开始界面需要找一些图片素材
- [ ] 各类型Button应当美化，设置样式的代码应当存入resources文件夹中
- [ ] 需要做更多的Activity，当下至少应当加入：
  - [ ] 选曲界面：最简单的做法可考虑使用ViewList控件
  - [ ] 结算界面：在TimeCountDown实例运行结束后传入分数等参数进行结算界面的展示
  - [ ] 设置界面：控制音量等
- [ ] 提示性的动画
#### 计时器、谱面与判定
- [x] 拆分谱面文件字符串，存入相应变量
- [x] 计时器的编写与调用
- [x] 判定函数的实现
### 当前的一些约定
- 谱面txt文件的编写可参考resources/raw/test.txt
- note采用Button控件实现，仅在Button上做UI优化
- GameActivity的主体只有一个GridLayout
- 游戏界面一共24个Button，3x7，每个Button都存入了二维数组，可以通过索引号直接获得其实例（还在优化）
### 正在进行的工作
#### branch BtBeautyfy
- [ ] 加界面
- [ ] 给MainActivity做美化
- [ ] 给buttons作样式设计
- [ ] 学习Android动画的实现方式，应用到GameActivity
#### branch ScoreCounter
- [x] 正在重新排列 3x7 button（其实本来应该再开个分支，但是属于之前已经被删掉的分支的失误，在这里弥补）
- [x] TimeCountDown的编写
- [x] 实现判定函数，在此之前需要考虑如何把txt文件中的参数合理的存进项目中
### 后续方向
- 找歌，编谱面
- OnTouchEvent的细节优化
- 封装功能（没时间就算了）
- 自制谱面的交互（没时间就算了）
