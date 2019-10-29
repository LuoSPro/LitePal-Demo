# LitePal-Demo
之前在GitHub上面看到过一个练习LitePal的小demo，感觉很适合入门，就自己尝试去实现一下，仅仅是简单学习一下LitePal的使用，没有做的很完整

![MyVideo_1.gif](https://upload-images.jianshu.io/upload_images/15748212-7b2b1d7d4fecf383.gif?imageMogr2/auto-orient/strip)
![MyVideo_2.gif](https://upload-images.jianshu.io/upload_images/15748212-15e620faceb4de50.gif?imageMogr2/auto-orient/strip)


### 现在就开始LitePal的尝试吧：
[注]这些都是可以在郭霖大神的博客或者书上能找到的步骤，这里不多叙述
[郭霖大神的博客](https://blog.csdn.net/guolin_blog/article/details/38556989)
##### 1.首先是添加依赖
看《第一行代码》上的LitePal是1.4.1版本的，但是我在用的时候，最新的已经是3.0.0了`implementation 'org.litepal.android:java:3.0.0'`
##### 2.在`AndroidManifest.xml`中添加`android:name="org.litepal.LitePalApplication"`
##### 3.新建一个`assets`目录，再在下面建一个 `litepal.xml`文件
```
<?xml version="1.0" encoding="utf-8" ?>
<litepal>
    <dbname value="BookStore"/>
    <version value="1"/>
    <list>
        <mapping class="com.example.litepaltest.Person"/>
    </list>
</litepal>
```
##### 4.建立一个`Person`类
只需要基本的姓名、性别、年龄即可
##### 5.再给demo一个布局
1. 需要一个创建数据库按钮（其实不太需要这个按钮，因为之前的步骤就能自动帮你创建一个数据库了），当时以为每次加载这个活动时，都去创建一次数据库，会使之前的数据消失，所以添加了这个按钮控制数据库的创建。最后发现不需要这样控制
2. 四个`EditText`分别去接收用户的值
3. 四个`Button`按钮分别实现数据库的增删改查
4. 添加一个 `ListView`去将数据库中的东西展示出来
##### 6. 注意事项
这个demo还有部分Bug，因为只是练手，所以并没有做得很完整
1. 增时，除了id外，其他的信息都要填（id只是为了删、改、查的时候用），添加时，即使输入了id，也是按照数据库的自增长id进行设置的
2. 删时，要注明删哪个id
3. 改时，写明全部信息
4. 查时，输入id即可
