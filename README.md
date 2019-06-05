# LOPH
## 当前应当解决的问题
### 界面的扩充和UI的美化
- 开始界面需要找一些图片素材
- 各类型Button应当美化，设置样式的代码应当存入resources文件夹中
- 需要做更多的Activity，当下至少应当加入：
  - 选曲界面：最简单的做法可考虑使用ViewList控件
  - 结算界面：在TimeCountDown实例运行结束后传入分数等参数进行结算界面的展示
  - 设置界面：控制音量等
- 提示性的动画
### 计时器、谱面与判定
- 拆分谱面文件字符串，存入相应变量
- 计时器的编写与调用
- 判定函数的实现
## 当前的一些约定
- 谱面txt文件的编写可参考resources/raw/test.txt
- note采用Button控件实现，仅在Button上做UI优化
- GameActivity的主体只有一个GridLayout
- 游戏界面一共24个Button，3x7，每个Button都存入了二维数组，可以通过索引号直接获得其实例（还在优化）
## 正在进行的工作
### branch BtBeautyfy
- 加界面
- 给MainActivity做美化
- 给buttons作样式设计
- 学习Android动画的实现方式，应用到GameActivity
### branch ScoreCounter
- 正在重新排列 3x7 button（其实本来应该再开个分支，但是属于之前已经被删掉的分支的失误，在这里弥补）【已解决】
- TimeCountDown的编写
- 实现判定函数，在此之前需要考虑如何把txt文件中的参数合理的存进项目中
## 后续方向
- 找歌，编谱面
- OnTouchEvent的细节优化
- 封装功能（没时间就算了）
- 自制谱面的交互（没时间就算了）
