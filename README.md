# Today_in_History
# 红岩寒假作业
## app名称：那年今日
## app功能：

![](https://github.com/deviser582/Today_in_History/raw/master/picture/Main_Activity.jpg)
### 1.主页面
#### 显示时间
>月，日，星期几
#### 按钮
>跳转至接口内容展示界面
#### 实现方法
>通过Calendar获取时间信息并更新TextView


![](https://github.com/deviser582/Today_in_History/blob/master/picture/Tab.gif)
### 2.接口内容展示界面
#### 界面设计
>TabLayout+ViewPager+RecyclerView+Fab
#### 主要功能
>从接口获取那年今日数据并通过RecyclerView展示
#### 按钮
>跳转至拓展功能选择界面
#### 实现方法
>1.在Fragment的onCreateView方法中写入RecyclerView<br>
2.在onCreate方法中新开线程进行网络请求，并将获得的String通过Handler传回主线程<br>
3.在主线程对数据进行解析，并添加RecyclerView

### 3.拓展功能选择页面
#### 主要功能
> 选择跳转至拓展功能
>>1.跳转至百度<br>
2.跳转至便签<br>
3.跳转至拨号页面并附上我的号码

![](https://github.com/deviser582/Today_in_History/raw/master/picture/Memo.gif
)
### 4.便签功能
#### 主要功能
>添加，删除一段文字，并自动储存，下一次打开可自动加载以前保存的内容
#### 实现方法
##### 使用SharedPreferences
>键值对num：<br>
* 通过num的值判断进入页面时加载几个RecyclerView<br>
* 添加便签时判断向哪个键值对存入数据<br>
* 删除便签后对键值对组数据的调整
##### 1.添加便签功能<br>
按下添加便签按钮，进入添加页面
>* 获取输入框输入的内容，并通过intent.putExtra()向便签页传递数据；<br>
按下确认按钮，返回便签页面；
>>* 读取num的值，进行判断后选择是否进行num++；<br>
通过覆写的onActivityResult()对传递的数据进行处理
>>>* 读取num的值，选择向memo_X中存入数据<br>
* 添加RecyclerView
##### 2.删除便签功能<br>
点击每个item的删除按钮进行删除
>* 读取需要删除的item的position；<br>
* 根据position选择从哪里开始键值对组的数据开始向上移动；<br>
* 读取并修改num的值，选择执行num--还是num-2；<br>
* 删除选中的那项
##### 3.便签数量达最大值之后添加便签的Toast
>选择添加后读取num的值，若等于最大数量，则弹出Toast

![](https://github.com/deviser582/Today_in_History/raw/master/picture/Call.gif
)
### 5.联系我功能
>通过
```
Intent intent = new Intent(Intent.ACTION_DIAL);
intent.setData(Uri.parse("tel:17783269045"));
startActivity(intent);
```

### 6.心得体会
>  经过这么一段时间的学习，说实话其实到寒假的时候都还只是个半吊子，交的作业也几乎只是把上课讲的复现一边而已[捂脸]，寒假开始的那几天还在天天复习Java。
之后开始写APP的时候，看了眼题目，毅然选择了自己感觉最简单的那一个开始写[捂脸]。
由于前面欠的账太多，几乎是一边翻书一边搜CSDN来写的（CSDNyyds！）好多不会写的，网上找范例，复制粘贴，然后再分析分析怎么改才能在自己的APP上面跑起来。
写道后面，还是慢慢变得熟练起来了，至少知道自己大概该怎么写了（虽然行数本来就不多）。
把接口实现完之后，发现自己写的这玩意太简单了，又想去搞搞其他题目里的接口，打开一看才知道自己是真的太菜了，不太清楚怎么实现，而且自我感觉时间也不太够了（拖延症晚期患者）。
后面自己就看书，然后搓了一个非常简单的便签出来，也算是让我的APP变得稍微没有这么简单了把[捂脸]。
虽然写的时候伤脑子，改bug的时候伤头发，但当每写出来一点东西的时候，还是很有成就感的。如果还有机会，我还是很愿意继续学下去的。

### 7.改进的地方
* 展示接口数据的地方有三个Fragment,每一个都分别用了一个网络请求，感觉太麻烦，希望可以只有一次网络请求，然后将数据分别传到每一个Fragment上
* 自己写的便签感觉实现功能太过复杂，有太多重复的方法（计划只是命名不同），希望可以有更简单的方法
* 便签目前只能加入一条String
* 写便签页面如果返回（不点击按钮），返回的是拓展功能菜单界面，而不是便签界面
