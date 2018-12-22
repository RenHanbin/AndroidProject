/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50701
Source Host           : localhost:3306
Source Database       : go2school

Target Server Type    : MYSQL
Target Server Version : 50701
File Encoding         : 65001

Date: 2018-12-22 21:59:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `answer`
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `answer_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `answer_content` varchar(100) CHARACTER SET utf8 NOT NULL,
  `answer_time` datetime NOT NULL,
  `question_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`answer_id`),
  KEY `question_answer` (`question_id`),
  KEY `answer_user` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of answer
-- ----------------------------
INSERT INTO `answer` VALUES ('1', '想上一个好大学就要使用升学问APP', '2018-11-26 15:43:14', '1', '1');
INSERT INTO `answer` VALUES ('2', '上大学根据孩子的兴趣吧', '2018-11-27 15:49:00', '2', '2');
INSERT INTO `answer` VALUES ('3', '升学问APP真的太好用了', '2018-11-28 15:51:17', '3', '3');

-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `article_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `article_title` varchar(50) NOT NULL,
  `article_content` varchar(2000) NOT NULL,
  `article_time` datetime NOT NULL,
  `article_img` varchar(100) NOT NULL,
  `writer_id` int(10) unsigned NOT NULL,
  `article_type` int(11) NOT NULL,
  PRIMARY KEY (`article_id`),
  KEY `article_writer` (`writer_id`),
  CONSTRAINT `articlewrite` FOREIGN KEY (`writer_id`) REFERENCES `writer` (`writer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', '美航天局称土星环正在消失，1亿年后或许不复存在', '新华社华盛顿12月17日电美国航天局一项新研究显示，土星正在“吃掉”环绕运行的土星环，这一主要由水冰组成的颗粒带可能不到1亿年后就不复存在了。\r\n\r\n发表在新一期美国行星科学期刊《伊卡洛斯》上的这项研究说，土星的引力正在将这些颗粒吸入上层大气，仅这种作用就会让土星环在3亿年后消失。如果将美国土星探测器“卡西尼”号此前发现的落向土星赤道的“环雨”考虑在内，土星环不到1亿年后就会消失。\r\n\r\n10月初，美国《科学》杂志发表了“卡西尼”号对土星环与行星相互作用的近距离观测结果，显示土星环内部的粒子等物质直接坠入土星大气层。\r\n\r\n研究人员称，土星环主要由大小从微米到米不等的水冰颗粒组成，颗粒的轨道速度本来与土星引力维持在平衡状态，但来自太阳的紫外光或宇宙流星尘轰击导致的等离子云大幅改变了这种平衡，让颗粒掉落下去。\r\n\r\n研究人员说，土星环正处于“中年”，现在能看到它很幸运，木星、天王星和海王星可能曾经也有这种环，但现在已经不明显了。', '2018-12-04 15:45:46', 'http://img.zcool.cn/community/01c81c5937d3a6a8012193a39a4d90.jpg@2o.jpg', '1', '1');
INSERT INTO `article` VALUES ('2', '高通遭殃：苹果如此反击iPhone禁售令！狠', '高通寻求福州法院寻求了对iPhone的禁售令，这彻底打乱了苹果的节奏，对于高通来说，就是要打乱苹果的节奏，倒逼他们重回谈判桌前。\r\n\r\n按照禁售令的要求，iPhone 6S、7、8和X系列机型都不能继续销售了，不过苹果强调，手机在国内正常销售，完全没有任何问题，涉及的专利非必要功能，通过软件更新就解决了。\r\n\r\n为了反击高通的禁售令，现在苹果四大供应商纬创、仁宝、和硕和富士康母公司鸿海精密已经放出话，要把高通告上了法庭，声称高通违反了反垄断法。\r\n\r\n这四大供应商认为，高通“非法”利用了在蜂窝技术方面早期的一些技术优势，设计出了一套反竞争的机制，阻碍别人去和高通进行竞争，阻碍了创新。这是以消费者的利益为代价，对中国、美国都是不利的，对创新和消费者也是不利的。\r\n\r\n据悉，这四大供应商要向高通索要赔偿金额为90亿美元。', '2018-12-05 15:46:53', 'http://img.mp.sohu.com/upload/20170607/786d7dc6776a416eaf6592729a2f498d_th.png', '4', '1');
INSERT INTO `article` VALUES ('3', 'ofo总部被“围” 退押金长龙从楼内排到楼外！', '小明告诉《证券日报》记者，其每日中午都会前往互联网金融中心附近食堂用餐，但12月17日中午却发现互联网金融中心楼下聚集着很多排队的人。“现场来了两辆警车，还拉了警戒线，最开始还以为是非法集资投资者在排队领钱，后来问了一下才知道是ofo用户来排队退押金。”小明对记者表示。\r\n\r\n据《证券日报》记者了解，ofo北京总部位于互联网金融中心5楼，12月17日当日，互联网金融中心中拉了一条条警戒线，并专门隔离了3架电梯供ofo用户使用。但即便如此，《证券日报》记者注意到，前往ofo北京总部寻求退还押金的用户仍然从楼内排到了楼外。\r\n\r\n对于ofo用户前往北京总部退押金一事，ofo方面谈到：“并无现场退押金情况，和线上退押并无区别。”但有互联网金融中心现场用户直言：“软件上能退谁还来这呀？”\r\n\r\n不过，即便到了ofo北京总部，想要退押金仍然没那么容易。据现场的ofo用户透露：“今天没退钱，只是去前台登记，登记之后三天之后押金会退到账上。”\r\n\r\n12月17日晚间，ofo方面发布公告称，提交线上申请退押金的用户，后台系统会根据申请提交的顺序进行相关信息审核与收集，核实完毕后用户将进入退押金序列，ofo将按顺序退款；如果用户到现场进行登记，ofo方面会将搜集到的相关信息按时间先后顺序并入线上退押金序列中；如有线下登记的用户此前已经发起退款申请，则按此前的队列信息为准。\r\n\r\nofo方面表示，of', '2018-12-06 15:47:37', 'http://5b0988e595225.cdn.sohucs.com/images/20180524/ba57f10d665f48bcaf8a313eaacdd7c3.jpeg', '2', '1');
INSERT INTO `article` VALUES ('4', '学姐给你说政治', ' 暑假：政治需不需要报个班？我在这里要说的是，政治班的视频必须看。看暑假强化班的视频，听老师的讲解，梳理一遍知识点，了解政治具体考察什么内容，是这个时间段需要做的事情。课看哪家的不需要太纠结，都差不多，认准一家就好。选口碑好一点的，大一点的机构就好。\r\n\r\n本阶段参考书：不需要自己买，每个视频课都会有讲义，下载讲义打印，老师会根据讲义划重点，跟着讲义就好。\r\n\r\n本阶段需要达到的目标：对政治有个大体的把握，明白每科考什么，怎么考，最好能在老师的讲解中建立起自己的框架体系。本阶段不需要去背，也不需要大量练题，背你也是白背，练一样白练，你会忘记的。只需要浏览讲义，建立框架即可。\r\n\r\n9-10月：此阶段主要需要解决的就是选择题的问题了。买一本市面上主流的习题册，至少2刷。在大纲解析出来之前，可以读强化班的讲义，读完一章做相应章节的选择题。错题勾画出来，看看是记忆问题，还是理解出错，或者是掉入陷阱，有针对性的解决。11月之前，一样不需要背诵，大题不用复习。\r\n\r\n 暑假：政治需不需要报个班？我在这里要说的是，政治班的视频必须看。看暑假强化班的视频，听老师的讲解，梳理一遍知识点，了解政治具体考察什么内容，是这个时间段需要做的事情。课看哪家的不需要太纠结，都差不多，认准一家就好。选口碑好一点的，大一点的机构就好。\r\n\r\n 暑假：政治需不需要报个班？我在这里要说的是，政治班的视频必须看。看暑假', '2018-12-12 16:08:14', 'http://5b0988e595225.cdn.sohucs.com/images/20170901/cdee30fd66184fbb9a0ddd7c30dc1520.jpeg', '2', '2');
INSERT INTO `article` VALUES ('5', '学长给你说数学', '数学被称为文科生软肋，很多文科生对数学有种天然的畏惧感，甚至会抱着一种我就是因为数学不好才来学文科的想法，这种思维首先就应该摒弃。我虽然是一名文科生，但是在所学科目中，数学可以说是我最感兴趣的一门了，因为感兴趣所以才会愿意去做题，去花时间，去下功夫。\r\n\r\n为什么会对数学产生兴趣，是因为数学做题思考过程中所给予的战胜感，还有摸索到方法时所带来的欢喜，再功利一点儿说，由于语文和英语学科在分数上很难同别人拉开差距，一门数学甩别人二三十分难道还不够让人心动吗。事实上高中数学学习还是有很多套路可寻的，只要掌握数学学习的正确方法，探索到适合自己的路子，数学学习还真的有那么点儿意思（言外之意是，甩别人二三十分不是梦）。', '2018-12-07 16:08:49', 'http://imgsrc.baidu.com/imgad/pic/item/c9fcc3cec3fdfc035a9f2f95df3f8794a4c2263c.jpg', '1', '2');
INSERT INTO `article` VALUES ('6', '学姐给你说物理', '初中的时候我的语文成绩是非常不错的，但是到高中之后，议论文的作文实在是难以下笔，渐渐失去了对语文的热情，再加上是高考第一门，略微有点小紧张，导致语文发挥的不好。所以啊学弟妹们，心态很重要，热情也很重要，在这种生死攸关的时刻，没有热情也要培养热情。其实我的理综也考得不是特别高啦，尤其是那年理综的化学难度比较大。但是对我来说还是比较满意的。\r\n\r\n高中的学习相对于大学来说其实是比较容易的（上了大学以后你就知道啦）。', '2018-12-06 16:09:10', 'http://imgsrc.baidu.com/imgad/pic/item/c9fcc3cec3fdfc035a9f2f95df3f8794a4c2263c.jpg', '3', '2');
INSERT INTO `article` VALUES ('7', '学长给你说英语', '今天我们要说的是一句及其日常的口语：给你。\r\n\r\n受到中式思维的影响，我们经常会条件反射地说：give you.\r\n\r\n但其实这是非常“中式英语”的。\r\n\r\n英语中，give you后面一般都要加上一个具体的东西，比如：\r\n\r\nI can give you some apples.\r\n\r\n我可以给你一些苹果。\r\n\r\nGive you本身，是很少单独使用的。\r\n\r\n要表达“给你”、“拿去”等意思，到底应该怎么说呢？\r\n\r\n其实我们小学就学过了，那就是Here you are或者Here you go.\r\n\r\n例如：\r\n\r\n——Can I read the book right now?\r\n\r\n——我现在能读这本书吗？\r\n\r\n——Sure. Here you are.\r\n\r\n——当然。给你。\r\n\r\nHere you go. This is your passport.\r\n\r\n给你。这是你的护照。', '2018-12-07 16:09:43', 'http://img.zcool.cn/community/0163a658e4e227a801219c77193d3a.jpg@1280w_1l_2o_100sh.jpg', '4', '2');
INSERT INTO `article` VALUES ('8', '艺考季大幕开启 浙江美术高考生破两万', '截至12月20日，全国20多个省份和直辖市结束了美术联考。上周末，浙江省2.16万美术生参加了全省美术联考，比去年增加了2300人。参考人数增加，意味着竞争越加激烈残酷。而对艺考生们来说，他们的高考冲刺之路才刚刚开始。接下来，迎接他们的，是比联考难度更大的各大院校的校考。', '2018-12-22 13:31:26', 'http://img4.imgtn.bdimg.com/it/u=1507379541,4105844699&fm=26&gp=0.jpg', '1', '1');

-- ----------------------------
-- Table structure for `city`
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `city_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `city_name` varchar(255) NOT NULL,
  `city_gdp` double NOT NULL,
  `city_salary` int(11) NOT NULL,
  `city_img` varchar(255) DEFAULT NULL,
  `province_id` int(10) unsigned NOT NULL,
  `city_type_id` int(10) unsigned NOT NULL,
  `city_content` varchar(2000) NOT NULL,
  PRIMARY KEY (`city_id`),
  KEY `city_province` (`province_id`),
  KEY `city_types` (`city_type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES ('1', '哈尔滨市', '55283', '3834', 'https://goss4.vcg.com/creative/vcg/400/new/a83f972796b04e2283e409d0c65bfca4.jpg', '1', '2', '哈尔滨，简称“哈”，别称“冰城”，是黑龙江省省会、副省级市、特大城市、中国东北地区中心城市之一，哈尔滨都市圈核心城市，是东北北部交通、政治、经济、文化、金融中心，也是中国省辖市中陆地管辖面积最大、户籍人口居第三位的特大城市.');
INSERT INTO `city` VALUES ('2', '大庆市', '85977', '5718', 'https://goss2.vcg.com/creative/vcg/400/version23/VCG216aef34cc1.jpg', '1', '3', '大庆，别称油城、百湖之城，是中国最大的石油石化基地，是黑龙江省地级市，位于黑龙江省西南部，是黑龙江省省域副中心城市 [1]  ，哈长城市群区域中心城市 [2]  。综合实力位列全国地级城市第11位 [3]  ，中国城市财力50强 [4]  新二线城市 [5]  。');
INSERT INTO `city` VALUES ('3', '长春市', '86931', '6357', 'https://goss2.vcg.com/creative/vcg/400/version23/VCG41170093299.jpg', '2', '2', '长春，简称“长”，别称“春城”，古称“喜都”、“茶啊冲”、“黄龙府”，是吉林省省会、副省级市、东北亚经济圈中心城市，是国务院定位的中国东北地区中心城市之一，国家历史文化名城，我国重要的工业基地和综合交通枢纽。');
INSERT INTO `city` VALUES ('4', '吉林市', '1800.6', '4980', 'https://goss1.vcg.com/creative/vcg/400/version23/VCG41170093329.jpg', '2', '3', '吉林市是中国唯一省市同名的城市。东北地区和吉林省重要的交通枢纽中心城市和新型工业基地。吉林省第二大城市。吉林省原省会及国务院批准设立的较大的市，具有雾凇之都、中国北方特色的旅游城市，国家历史文化名城、中国书法城等名誉。荣登福布斯中国大陆最佳商业城市排行榜。首批国家新型城镇化综合试点地区。');
INSERT INTO `city` VALUES ('5', '沈阳市', '70722', '4460', 'https://goss1.vcg.com/creative/vcg/400/version23/VCG41534043255.jpg', '3', '2', '沈阳，简称“沈”，别称盛京、奉天，是辽宁省省会、 [1]  副省级市、沈阳都市圈核心城市，国务院批复确定的中国东北地区重要的中心城市、先进装备制造业基地和科技创新中心，全市下辖10区、2县，代管1县级市，面积1.29万平方公里，常住人口829.1万人，城镇化率80.55%，是环渤海以及东北唯一的特大城市。');
INSERT INTO `city` VALUES ('6', '鞍山市', '1597.5', '5028', 'https://goss.vcg.com/creative/vcg/400/new/acf168cf6d8a4518af2d1a0b361629d3.jpg', '3', '3', '鞍山，简称鞍，辽宁省省辖市，是辽宁省第三大城市，我国重要的钢铁工业基地，辽中南地区重要的中心城市 [1]  。是国务院批准的具有地方立法权的较大的市，沈阳经济区副中心城市，因市区南部一座形似马鞍的山峰而得名。鞍山地处环渤海经济区腹地，辽东半岛中部，位于沈大黄金经济带的重要支点，是辽宁中部城市群与辽东半岛开放区的重要连接带');
INSERT INTO `city` VALUES ('7', '上海市', '20101.33', '7216', 'https://goss1.vcg.com/creative/vcg/400/version23/VCG41526528747.jpg', '4', '1', '上海，简称“沪”或“申”，是中国共产党的诞生地，中华人民共和国直辖市、国家中心城市、超大城市，国际经济、金融、贸易、航运、科技创新中心和综合交通枢纽，首批沿海开放城市。上海地处长江入海口，是长江经济带的龙头城市、G60科创走廊核心城市。隔东中国海与日本九州岛相望，南濒杭州湾，北、西与江苏、浙江两省相接。');
INSERT INTO `city` VALUES ('8', '温州市', '5453.2', '5603', 'https://goss4.vcg.com/creative/vcg/400/new/092c4c8b0fa447bbbff6b5f3cb852acd.jpg', '5', '2', '温州，简称“瓯”，浙江省地级市；浙江省区域中心城市之一。温州位于浙江省东南部，瓯江下游南岸。全市陆域面积11612.94平方千米，海域面积约11000平方千米。全市辖4个市辖区、5个县、2个县级市。2017年常住人口921.5万人。。');
INSERT INTO `city` VALUES ('9', '湖州市', '2476.1', '12746', 'https://goss4.vcg.com/creative/vcg/400/new/VCG211115973864.jpg', '5', '3', '湖州市是浙江省下辖地级市，是“长三角城市群”成员城市 [1]  、环杭州湾大湾区核心城市、G60科创走廊中心城市，地处浙江省北部，东邻嘉兴，南接杭州，西依天目山，北濒太湖，与无锡、苏州隔湖相望，是环太湖地区因湖而得名的城市。处在太湖南岸，东苕溪与西苕溪汇合处。下设2区3县，面积5820.13平方千米 [2]  。2016年户籍人口264.84万。');
INSERT INTO `city` VALUES ('10', '北京市', '17801', '7828', 'https://goss4.vcg.com/creative/vcg/400/version23/VCG21ef1a15bda.jpg', '6', '1', '北京，简称“京”，是中华人民共和国的首都、直辖市、国家中心城市、超大城市、国际大都市，全国政治中心、文化中心、国际交往中心、科技创新中心和综合交通枢纽，是中国共产党中央委员会、中华人民共和国中央人民政府、全国人民代表大会、中国人民政治协商会议全国委员会、中华人民共和国中央军事委员会所在地，也是中部战区司令部驻地。');
INSERT INTO `city` VALUES ('11', '石家庄市', '5857.8', '4759', 'https://goss4.vcg.com/creative/vcg/400/new/b5f84eefeeb345b4b9d5405442e478c7.jpg', '7', '2', '石家庄，简称“石”，旧称石门，是河北省省会，地处河北省西南部。截至2016年底，石家庄辖区总面积15848平方公里，其中市区面积2206平方公里，全市常住人口1078.46万人，下辖8个区、11个县，代管3个县级市。');
INSERT INTO `city` VALUES ('12', '廊坊市', '2880.6', '5442', 'https://goss4.vcg.com/creative/vcg/400/new/06af820b61dc4874bdc3f9cf5068f2d9.jpg', '7', '3', '廊坊市，河北省地级市，位于河北省中部偏东，北临首都北京，东与天津交界，南接沧州，西连保定，受地质构造的影响，廊坊市大部处于凹陷地区；地处中纬度地带，属暖温带大陆性季风气候；地处京津冀城市群核心地带，环渤海腹地。廊坊市辖两个区、两个县级市及六个县，总面积6429平方千米，市区面积54平方千米。2017年常住人口474.1万人。');
INSERT INTO `city` VALUES ('13', '洛阳市', '3782.9', '5445', 'https://goss4.vcg.com/creative/vcg/400/new/dc82f9ff8ff54091b240d6ed66570edf.jpg', '8', '3', '洛阳，古称斟鄩、西亳、洛邑、雒阳、洛京、京洛、神都、洛城等，位于河南省西部、黄河中下游，因地处洛河之阳而得名，是国务院首批公布的国家历史文化名城，中国四大古都之一，世界文化名城。');
INSERT INTO `city` VALUES ('14', '宜昌市', '3857.17', '5111', 'https://goss3.vcg.com/creative/vcg/400/version23/VCG41508278240.jpg', '9', '3', '宜昌，湖北省辖地级市，古称夷陵，位于湖北省西南部、长江上中游分界处，建制历史逾两千年。“宜昌”之名始于东晋，市的建制始于解放初，于1992年设立地级市。宜昌市地理环境复杂多样，地形比较复杂，高低相差悬殊；位于中亚热带与北亚热带的过渡地带，属亚热带季风性湿润气候。2017年，全市总面积21227平方千米，辖五区、三市、五县，2017年常住人口413.56万人。');
INSERT INTO `city` VALUES ('15', '佛山市', '9500', '5573', 'https://goss1.vcg.com/creative/vcg/400/version23/VCG41590217479.jpg', '10', '2', '佛山，前身为管辖珠江三角洲的粤中行署、佛山专区， [1]  现广东省省辖市，中国重要的制造业基地，国家历史文化名城，珠三角地区西翼经贸中心和综合交通枢纽 [2]  。佛山位于广东省中部，地处珠三角腹地，毗邻港澳，东接广州，南邻中山。是“广佛都市圈”、“广佛肇经济圈”、“珠江-西江经济带”的重要组成部分，全国先进制造业基地、广东重要的制造业中心，在广东省经济发展中处于领先地位。');
INSERT INTO `city` VALUES ('16', '珠海市', '2564.73', '6386', 'https://goss4.vcg.com/creative/vcg/400/new/2a962123c92a4a28a4d90e7bff39be1a.jpg', '10', '3', '珠海，广东省地级市，珠江三角洲中心城市之一，东南沿海重要的风景旅游城市 [1]  。地处北纬21°48′～22°27′、东经113°03′～114°19′之间。 [2]  位于广东省珠江口的西南部，东与香港隔海相望，南与澳门相连，西邻江门市新会区、台山市，北与中山市接壤。设有拱北、九洲港、珠海港、万山、横琴、斗门、湾仔、珠澳跨境工业区等8个国家一类口岸，是珠三角中海洋面积最大、岛屿最多、海岸线最长的城市，素有“百岛之市”之称。');
INSERT INTO `city` VALUES ('17', '三亚市', '529.25', '5768', 'https://goss1.vcg.com/creative/vcg/400/version23/VCG41569011383.jpg', '11', '3', '三亚，简称崖，古称崖州，别称鹿城。是海南省下辖地级市，位于海南岛的最南端。三亚东邻陵水县，西接乐东县，北毗保亭县，南临南海，介于北纬18°09′34″~18°37′27″、东经108°56′30″~109°48′28″之间。 [1]  三亚市陆地总面积1919.58平方千米，海域总面积6000平方千米。东西长91.6千米，南北宽51公里，下辖四个区。 [2]  2017年，全市常住人口为76.42万人，聚居了汉族、黎族、苗族、回族等20多个民族。');
INSERT INTO `city` VALUES ('18', '重庆市', '11459', '5316', 'https://goss4.vcg.com/creative/vcg/400/version23/VCG21gic18853167.jpg', '12', '2', '重庆，简称渝或巴， [1]  是中华人民共和国中西部唯一的直辖市、国家中心城市 [2]  、超大城市、国际大都市，长江上游地区的经济、金融、科创、航运和商贸物流中心， [3]  西部大开发重要的战略支点、“一带一路”和长江经济带重要联结点以及内陆开放高地； [1]  [4]  既以江城、雾都著称，又以山城扬名。');
INSERT INTO `city` VALUES ('19', '绵阳市', '2074.75', '5044', 'https://goss1.vcg.com/creative/vcg/400/version23/VCG21gic18852097.jpg', '13', '3', '绵阳市，隶属于四川省，位于四川盆地西北部，涪江中上游地带。东邻广元市的青川县、剑阁县和南充市的南部县、西充县；南接遂宁市的射洪县；西接德阳市的罗江区、中江县、绵竹市；西北与阿坝藏族羌族自治州和甘肃省的文县接壤。介于北纬30°42′—33°03′、东经103°45′—105°43′之间，总面积20248.4平方千米。');
INSERT INTO `city` VALUES ('20', '咸阳市', '2340.66', '4588', 'https://goss1.vcg.com/creative/vcg/400/version23/VCG21gic18852097.jpg', '14', '3', '咸阳，陕西省地级市，是中国首个封建王朝“大秦帝国”的都城，位于陕西省八百里秦川腹地，渭水穿南，嵕山亘北，山水俱阳，故称咸阳。咸阳东邻省会西安，西接国家级杨凌农业高新技术产业示范区，西北与甘肃接壤。辖2区2市9县，总面积10189.4平方公里(其中644.56平方公里被西安托管 [1-3]  ，亦未包含杨陵区）。2017年末全市常住人口437.60万，中心城区人口91.5万，位居陕西省第三位，仅次于西安、宝鸡。 [4-5]  咸阳是中国甲级对外开放城市、国家级历史文化名城、全国双拥模范城市、国家卫生城市、中国魅力城市、中国地热城、全国十佳宜居城市、首批中国优秀旅游城市、全国精神文明创建工作先进市及中华养生文化名城。');
INSERT INTO `city` VALUES ('21', '兰州市', '2642', '6638', 'https://goss1.vcg.com/creative/vcg/400/version23/VCG21gic18852097.jpg', '15', '2', '兰州，简称“兰”，是甘肃省省会，中国西北地区重要的工业基地和综合交通枢纽，西部地区重要的中心城市之一，西陇海兰新经济带重要支点， [1]  西北地区重要的交通枢纽和物流中心，是新亚欧大陆桥中国段五大中心城市之一，是中国华东、华中地区联系西部地区的桥梁和纽带，西北的交通通信枢纽和科研教育中心，丝绸之路经济带的重要节点城市，也是中国人民解放军西部战区陆军机关驻地。');
INSERT INTO `city` VALUES ('22', '成都市', '13889.39', '5608', 'https://goss1.vcg.com/creative/vcg/400/version23/VCG21gic18852097.jpg', '13', '2', '成都是四川省省会，简称蓉，别称蓉城、锦城，副省级市，特大城市，位于四川盆地西部，成都平原腹地，境内地势平坦、河网纵横、物产丰富、农业发达，属亚热带季风性湿润气候。 [1]  下辖11区、4县，代管5县级市，总面积14335平方千米；2017年常住人口1604.5万人。');
INSERT INTO `city` VALUES ('23', '深圳市', '22438.39', '6833', 'https://goss1.vcg.com/creative/vcg/400/version23/VCG21gic18852097.jpg', '10', '1', '深圳，简称“深”，别称“鹏城”，是中国四大一线城市之一，广东省省辖市、计划单列市、副省级市、国家区域中心城市、超大城市，国务院定位的全国经济中心城市和国际化城市、国家创新型城市、国际科技产业创新中心、全球海洋中心城市、国际性综合交通枢纽，中国三大全国性金融中心之一。');

-- ----------------------------
-- Table structure for `city_type`
-- ----------------------------
DROP TABLE IF EXISTS `city_type`;
CREATE TABLE `city_type` (
  `city_type_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `city_type_name` varchar(255) NOT NULL,
  PRIMARY KEY (`city_type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of city_type
-- ----------------------------
INSERT INTO `city_type` VALUES ('1', '一线');
INSERT INTO `city_type` VALUES ('2', '二线');
INSERT INTO `city_type` VALUES ('3', '其他');

-- ----------------------------
-- Table structure for `collection_article`
-- ----------------------------
DROP TABLE IF EXISTS `collection_article`;
CREATE TABLE `collection_article` (
  `user_id` int(10) unsigned NOT NULL,
  `article_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`article_id`),
  KEY `collection_article` (`article_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collection_article
-- ----------------------------
INSERT INTO `collection_article` VALUES ('1', '1');
INSERT INTO `collection_article` VALUES ('1', '2');
INSERT INTO `collection_article` VALUES ('2', '3');
INSERT INTO `collection_article` VALUES ('3', '2');
INSERT INTO `collection_article` VALUES ('3', '3');

-- ----------------------------
-- Table structure for `collection_major`
-- ----------------------------
DROP TABLE IF EXISTS `collection_major`;
CREATE TABLE `collection_major` (
  `major_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`major_id`,`user_id`),
  KEY `collection_user3` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collection_major
-- ----------------------------
INSERT INTO `collection_major` VALUES ('1', '1');
INSERT INTO `collection_major` VALUES ('2', '1');
INSERT INTO `collection_major` VALUES ('4', '3');
INSERT INTO `collection_major` VALUES ('5', '3');
INSERT INTO `collection_major` VALUES ('19', '1');

-- ----------------------------
-- Table structure for `collection_question`
-- ----------------------------
DROP TABLE IF EXISTS `collection_question`;
CREATE TABLE `collection_question` (
  `user_id` int(10) unsigned NOT NULL,
  `question_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`question_id`),
  KEY `collection_question` (`question_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collection_question
-- ----------------------------
INSERT INTO `collection_question` VALUES ('1', '1');
INSERT INTO `collection_question` VALUES ('2', '2');
INSERT INTO `collection_question` VALUES ('3', '3');

-- ----------------------------
-- Table structure for `collection_school`
-- ----------------------------
DROP TABLE IF EXISTS `collection_school`;
CREATE TABLE `collection_school` (
  `user_id` int(10) unsigned NOT NULL,
  `school_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`school_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collection_school
-- ----------------------------
INSERT INTO `collection_school` VALUES ('1', '2');
INSERT INTO `collection_school` VALUES ('1', '3');
INSERT INTO `collection_school` VALUES ('1', '10');
INSERT INTO `collection_school` VALUES ('2', '3');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `comment_content` varchar(50) DEFAULT NULL,
  `comment_time` datetime NOT NULL,
  `answer_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `article_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `comment_answer` (`answer_id`),
  KEY `comment_user` (`user_id`),
  KEY `comment_article` (`article_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '真的假的？（评论内容）', '2018-11-28 15:45:15', '1', '1', '1');
INSERT INTO `comment` VALUES ('2', '真的吧', '2018-11-26 17:00:07', '2', '2', '2');
INSERT INTO `comment` VALUES ('3', '恩恩，挺好用的', '2018-12-05 17:00:27', '3', '3', '3');

-- ----------------------------
-- Table structure for `follow`
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow` (
  `follow_user_id` int(10) unsigned NOT NULL,
  `followed_user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`follow_user_id`,`followed_user_id`),
  KEY `followed_user` (`followed_user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of follow
-- ----------------------------
INSERT INTO `follow` VALUES ('1', '2');
INSERT INTO `follow` VALUES ('1', '3');
INSERT INTO `follow` VALUES ('2', '1');
INSERT INTO `follow` VALUES ('2', '3');
INSERT INTO `follow` VALUES ('3', '1');

-- ----------------------------
-- Table structure for `major`
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major` (
  `major_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `major_name` varchar(20) NOT NULL,
  `major_workPercent` double(6,2) DEFAULT NULL,
  `major_salary` int(11) DEFAULT NULL,
  `major_type_id` int(11) unsigned NOT NULL,
  `major_want` int(11) DEFAULT NULL,
  `major_need` int(11) DEFAULT NULL,
  `major_study` double DEFAULT NULL,
  `major_go` double DEFAULT NULL,
  `major_introduce` varchar(200) DEFAULT NULL,
  `major_subject` varchar(200) DEFAULT NULL,
  `major_work` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`major_id`),
  KEY `majorstype` (`major_type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES ('1', '软件工程', '96.71', '9001', '8', '800', '600', '60', '87', '软件工程是一门研究用工程化方法构建和维护有效的、实用的和高质量的软件的学科。', '操作系统，数据结构，离散数学，面向对象程序设计，网络原理，计算机组成原理', '各行各业，比如工业，农业，银行，航空，政府部门等');
INSERT INTO `major` VALUES ('2', '材料物理', '96.20', '8862', '8', '1000', '800', '60', '85', '材料物理专业培养较系统地掌握材料科学的基本理论与技术，具备材料物理相关的基本知识和基本技能，能在材料科学与工程及与其相关的领域从事研究、教学、科技开发及相关管理工作的材料物理高级专门人才。', '基础物理，近代物理', '适宜到材料相关的企业、事业、技术和行政管理部门从事应用研究、科技开发、生产技术和管理工作，适宜到科研机构、高等学校从事科学研究和教学工作。\r\n');
INSERT INTO `major` VALUES ('3', '汽车类综合', '95.68', '8786', '8', '800', '700', '50', '70', '该专业针对汽车后服务市场中美容装潢需求，根据市场上最新的汽车美容装潢工艺流程相结合教学。', '电工技术基础、机械制图与计算机绘图、汽车机械基础、汽车构造、汽车营销基础、汽车营销实务、汽车电气设备、汽车故障检测技术', '汽车制造、售后服务、车辆管理、汽车检测、汽车维修等');
INSERT INTO `major` VALUES ('4', '应用化学', '94.77', '8650', '7', '500', '560', '70', '60', '该专业培养具备化学方面的基础知识、基本理论、基本技能以及相关的工程技术知识和较强的实验技能，具有化学基础研究和应用基础研究方面的科学思维和科学实验训练，能在科研机构、高等学校及企事业单位等从事科学研究、教学工作及管理工作的高级专门人才。', '无机化学、分析化学、有机化学、物理化学、仪器分析、结构化学基础、精细有机品化学、高分子化学、波谱分析、应用电化学', '在化工、炼油、冶金、能源、轻工、医药、环保和军工等部门从事工程设计、技术开发、生产技术管理和科学研究等方面工作');
INSERT INTO `major` VALUES ('5', '生物科学', '94.26', '8622', '7', '600', '550', '70', '83', '该专业主要培养学生学习生物科学技术方面的基本理论、基本知识，学生将受到应用基础研究和技术开发方面的科学思维和科学实验训练，进而具有较好的科学素养及初步的教学、研究、开发与管理的基本能力。', '动物生物学、植物生物学、微生物学、生物化学、遗传学、细胞生物学、分子生物学、普通生态学、植物生理学、人体解剖学等', '主要在科研机构、高等院校以及国家机关等部门从事科研、教学和高级管理工作。');
INSERT INTO `major` VALUES ('6', '金融学', '72.30', '8006', '2', '304', '205', '83.3', '60.2', '该专业是从经济学中分化出来的应用经济学科，是以融通货币和货币资金的经济活动为研究对象，具体研究个人、机构、政府如何获取、支出以及管理资金以及其他金融资产的学科。', '政治经济学、西方经济学、财政学、国际经济学、货币银行学、国际金融管理、证券投资学、保险学、商业银行业务管理、中央银行业务', '主要的就业方向是银行、保险公司、证券公司、投资公司等金融机构。');
INSERT INTO `major` VALUES ('7', '经济学', '91.74', '8000', '2', '700', '600', '70', '67', '经济学专业是(包括经济学方向和投资经济方向)为适应我国市场经济发展需要而设立的一个理论兼应用型本科专业。', '政治经济学，资本论，国际经济学，货币银行学，财政学，市场营销，国际金融，企业管理', '主要在综合经济管理部门、政策研究部门、金融机构和企业等，从事经济分析、预测、市场营销和经济管理工作');
INSERT INTO `major` VALUES ('8', '财政学', '92.15', '8000', '2', '600', '400', '80', '87', '财政学专业属于应用经济学科，它主要研究政府部门在资金筹集和使用方面的理论、制度和管理方法，同时也研究企业在生产经营过程中的税收问题。', '政治经济学、西方经济学、货币银行学、国际经济学、财政学、国家预算、税收管理、国际税收、国有资产管理等。', '对外贸易，税务筹划，公务员等');
INSERT INTO `major` VALUES ('9', '哲学', '90.34', '6784', '1', '300', '100', '70', '88.5', '哲学专业是培养具有一定马克思主义哲学理论素养和系统的专业基础知识，能运用科学的世界观和方法论分析当代世界与中国的现实问题的应用型、复合型高级专门人才的学科。', '哲学概论、马克思主义哲学原理、中国哲学史、西方哲学史、科学技术哲学、伦理学、宗教学、美学、逻辑学、人类学、心理学', '可在国家机关、文教事业、新闻媒体、公司企业等部门从事行政、宣传、管理、教学、科研工作');
INSERT INTO `major` VALUES ('10', '逻辑学', '89.99', '5678', '1', '300', '200', '60', '58.7', '本专业以逻辑学为核心，横跨多学科，培养文理交叉、理论与应用相结合的复合型高级人才。', '哲学概论、马克思主义哲学、西方哲学、中国哲学、逻辑学导论、科学哲学导论、伦理学导论、宗教学导论、美学导论、数理逻辑', '可在高等院校、科研单位、国家机关及企事业管理部门从事逻辑学的教学、科研和应用方面的工作，从事计算机科学和语言学的科研和应用方面相关工作。');
INSERT INTO `major` VALUES ('11', '宗教学', '90.34', '6867', '1', '200', '50', '80', '90.2', '宗教学专业学生主要学习宗教学的基本理论，较全面地了解世界各大宗教的历史与现状，以及我国的宗教法规和政策，受到独立思考、分析问题、社会调研等方面的基本训练。', '马克思主义哲学原理、中国哲学史、西方哲学史、宗教学导论、佛教、道教、伊斯兰教、基督教、宗教社会学、、宗教伦理学', '咨询/顾问，行政/后勤，学术科研，教育培训，人事');
INSERT INTO `major` VALUES ('12', '社会学', '80.34', '5678', '3', '500', '300', '70', '84.3', '社会学是一门分析各种社会现象，研究社会中人的行为，探求如何解决社会问题的学科，研究领域涉及到我们身边的家庭、学校、企业到国家乃至国际社会。', '社会学，法学', '可在教育、科研机构、党政机关、新闻出版、企事业单位、社会团体从事社会研究与调查，政策研究与评估、社会规划与管理、发展研究与预测等');
INSERT INTO `major` VALUES ('13', '社会工作', '82.56', '5688', '3', '500', '400', '67.7', '67', '社会工作专业是政府为主体，社会力量广泛参与的，以社会工作、社会学、心理学等为主干学科基础，物业管理、医学、法学等为辅助学科，以助人自助为核心理念，以个案工作、小组工作、社区工作为直接工作方法的学科。', '社会学概论、社会工作概论、社会统计学 、社会调查研究方法', '民政、妇联、慈善机构、社会团体机构、社区服务机构、街道办事处、专业社工机构等');
INSERT INTO `major` VALUES ('14', '政治学与行政学', '83.21', '7894', '3', '600', '500', '78.2', '79.1', '政治学与行政学专业培养具有一定马克思主义理论素养和政治学、行政学方面的基本理论和专门知识，能在党政机关、新闻出版机构、企事业和社会团体等单位从事教学科研、行政管理等方面工作的政治学和行政学高级专门人才。', '政治学', '毕业生可以到党政机关、社会团体、新闻出版机构、教育及其他企事业单位从事科研、教学、行政管理以及其他有关专门业务工作。');
INSERT INTO `major` VALUES ('15', '教育学', '90.60', '7674', '4', '700', '800', '67.8', '77.4', '教育学是研究教育现象和教育问题、解释教育规律的科学。', '教育学，心理学', '各行各业，包括各类院校、学院，社区服务社，咨询组织，文化组织，还包括司法系统、国家级协会、委员会、研究与开发中心，政府教育部，甚至是金融机构和传媒行业');
INSERT INTO `major` VALUES ('16', '学前教育', '90.87', '5674', '4', '500', '400', '89.9', '68', '学前教育专业培养具备学前教育专业知识，能在托幼机构从事保教和研究工作的教师学前教育行政人员以及其他有关机构的教学、研究人才。', '、教育社会学、教育统计与测量、幼儿心理、学前儿童认知发展、幼儿卫生与保健、幼儿游戏理论、环境与儿童健康、家庭教育', '在各级各类学前教育机构，如幼儿园、托儿所、亲子园等单位任幼儿教学工作');
INSERT INTO `major` VALUES ('17', '特殊教育', '92.56', '5644', '4', '200', '400', '80', '68', '特殊教育专业培养具备普通教育和特殊教育的知识和能力，主要能在特殊教育机构及与特殊教育相关的机构从事特殊教育实践、理论研究、管理工作等方面的高级专门人才。', '特殊教育学、特殊儿童评估、特殊儿童病理学、教育听力学、特殊儿童早期干预、特殊学校教材教法、残疾儿童康复教育、心理测量', '可以到各种特殊教育学校以及与特殊教育相关的机构从事特殊教育实践、理论研究、管理等工作');
INSERT INTO `major` VALUES ('18', '中国少数民族语言文学', '93.35', '5409', '5', '200', '300', '78.5', '57', '中国少数民族语言文学专业培养具备有关少数民族语言文学全面系统知识，能在少数民族教育文化部门及相关单位从事有关少数民族语、文字、文学、文献的教学、研究、编辑、翻译、新闻、文学创作等方面工作的少数民族语言文学高级专门人才。', '中国语言文学', '主要到少数民族教育文化部门及相关单位从事有关少数民族语言文字、文学、文献的教学、研究、编辑、翻译、新闻、文学创作等方面工作。');
INSERT INTO `major` VALUES ('19', '古典文献', '80.43', '4590', '5', '300', '200', '67', '69', '古典文献专业培养具备中国古籍整理与古典文献学全面系统知识，能在教育、文化、出版部门，从事古籍整理、传统文化方面的实际工作、古典文献教学与研究工作的文献学高级专门人才。', '中国语言文学', '主要到新闻文艺出版部门、高校、科研机构和机关企业事业单位从事文学评论、汉语言文学教学与研究工作，以及文化、宣传方面的实际工作。');
INSERT INTO `major` VALUES ('20', '外语', '94.21', '7806', '5', '600', '500', '76', '67.7', '英语专业是培养具有扎实的英语语言基础和比较广泛的科学文化知识，能在外事、经贸、文化、新闻出版、教育、科研、旅游等部门从事翻译、研究、教学、管理工作的英语高级专门人才的学科。', '精读,泛读，听力，口语，写作，翻译等', '适合于外经贸各部委、贸易公司、涉外机构、外商投资企业、跨国公司、金融国贸等单位的文秘、翻译、业务人员或行政管理人员等工作');
INSERT INTO `major` VALUES ('21', '历史学', '90.82', '7857', '6', '500', '400', '68', '87.5', '历史学专业培养具有一定的马克思主义基本理论素养和系统的专业基本知识，有进一步培养潜能的史学专门人才，以及能在国家机关、文教事业、新闻出版、文博档案及各类介事业单位从事实际工作的应用型、复合型高级专门人才。', '中国通史、世界通史，史学理论、中国史学史、西方史学史、考古学通论、中外关系史、文化人类学、历史地理学、文献学、古代汉语', '就业方向相当广泛，毕业生可适应策划、咨询、管理和教学、研究等方面工作对高层次人才的需求。');
INSERT INTO `major` VALUES ('22', '考古学', '76.32', '5674', '6', '400', '300', '78', '82', '考古学是历史科学的重要组成部分，属于人文学科。简单地说，考古学就是根据古代人类遗留下来的实物，研究古代社会历史的科学。', '中国通史、世界上古史、中国考古学史、考古学导论、旧石器时代考古、新石器时代考古、夏商周考古、战国秦汉考古、三国两晋南北朝考古、隋唐考古、宋元明考古、田野考古等。', '可以应聘到大学或科研单位从事教学或科学研究工作，也可以到博物馆、拍卖行、文物商店或海关，从事文物保护、古玩鉴定以及拍卖等工作');
INSERT INTO `major` VALUES ('23', '博物馆学', '70.98', '5643', '6', '300', '200', '79', '81', '博物馆学专业是研究涉及博物馆物品的陈列等诸多问题的专业。', '博物馆学概论、博物馆陈列设计、博物馆藏品管理、博物馆经营管理、物质文化史、文化人类学、文物学概论、文物管理与法规、中国历史地理、古代工艺美术等', '可以到博物馆做史料研究，还可以在政府机关，当然是涉及到文化这一部分的企事业单位工作，还可以到高等学校、专科学校担任教师和科研工作');
INSERT INTO `major` VALUES ('24', '园艺', '93.37', '5678', '9', '500', '300', '86.5', '81.5', '园艺专业培养具备生物学和园艺学的基本理论、基本知识和基本技能，能在农业、商贸、园林管理等领域和部门从事与园艺科学有关的技术与设计、推广与开发、经营与管理、教学与科研等工作的高级科学技术人才。', '植物学、生物化学、植物生理学、植物生理与生物化学、应用概率统计、遗传学、土壤学、农业生态学、园艺植物育种学、园艺植物栽培学', '可在政府行政管理部门和事业单位、园艺和园林企业、高等院校、科研部门从事观赏园艺相关的行政管理、技术推广、设计与技术研发、经营与管理、教学和科研等工作');
INSERT INTO `major` VALUES ('25', '植物保护', '76.34', '3457', '9', '600', '500', '67.4', '77', '植物保护专业培养具备植物保护科学基本知识和基本技能，从事植物病虫、杂草、鸟兽害的预测预报和防治的高级技术应用性专门人才。', '普通植物病理学、普通昆虫学、农业植物病理学、农业昆虫学、植物化学保护。', '就业于各级农业、林业部门；进出口检疫检验部门；海关部门；食品药品安全监管部门；农药生产企业；各级基层农场、林场、森林公园等。');
INSERT INTO `major` VALUES ('26', '茶学', '87.54', '3464', '9', '100', '100', '66.6', '76', '茶学研究的内容，从大的方面来划分，可以划分作两大部分：茶科学和茶文化学。该专业的培养目标是重点培养具备农业生物科学、食品科学和茶学等方面的基本理论、基本知识和基本技能，能在农业、工业、商贸等领域或部门从事与茶学有关的技术与设计、推广与开发、经营与管理、教学与科研等工作的高级科学技术人才。', '植物生理与生物化学、应用概率统计、遗传学、土壤学、农业生态学、茶树栽培与育种学、茶叶生物化学、茶叶机械、茶叶加工学、茶叶审评与检验、经济管理与营销。', '可在农业、工业、商贸等领域或部门从事与茶学有关的技术与设计、推广与开发、经营与管理、教学与科研等工作。');
INSERT INTO `major` VALUES ('27', '预防医学', '94.26', '7654', '10', '500', '400', '70.5', '78', '预防医学是从医学科学体系中分化出来的，它是研究预防和消灭病害，讲究卫生，增强体质，改善和创造有利于健康的生产环境和生活条件的科学。', '基础医学、预防医学', '预防医学专业就业领域很广，涉及医学和非医学领域的各相关专业。');
INSERT INTO `major` VALUES ('28', '临床医学', '91.46', '6534', '10', '600', '500', '79.6', '67', '临床医学专业是一门实践性很强的应用科学专业，致力于培养具备基础医学、临床医学的基本理论和医疗预防的基本技能；能在医疗卫生单位、医学科研等部门从事医疗及预防、医学科研等方面工作的医学高级专门人才。', '人体解剖学、组织学与胚胎学、生物化学、神经生物学、生理学、医学微生物学、医学免疫学、病理学、药理学、人体形态学实验、医学生物学实验', '毕业后可以在医疗卫生机构从事临床各科的医疗、预防工作及医学教学和研究工作。');
INSERT INTO `major` VALUES ('29', '医学影像学', '97.85', '6543', '10', '600', '500', '65.8', '65.3', '该专业培养具有基础医学、临床医学和现代医学影像学的基本理论知识及能力，能在医疗卫生单位从事医学影像诊断、介入放射学和医学成像技术等方面工作的医学高级专门人才。', '基础医学、临床医学、医学影像学。', '可在各类医疗机构、防疫机构、医学科研部门、血站等单位，从事临床影像技术、功能检查等技术工作，也可到医疗设备公司工作。');
INSERT INTO `major` VALUES ('30', '武器系统与发射工程', '78.05', '7896', '11', '400', '400', '68.9', '65.3', '本专业培养具备武器系统总体和战斗载荷发射技术以及机械工程和自动化等方面的基础理论知识和工程实践能力，能在有关科研单位、高等学校、生产企业和管理部门从事系统设计、技术开发、产品制造、实验测试和科技管理方面工作的高级工程技术人才。', '兵器发射理论与技术，工程力学，机械工程', '可在兵器科学研究院等科研院所工作，在部队工作，进行武器的研究和制造，从事尖端武器的研究或管理工作。');
INSERT INTO `major` VALUES ('31', '弹药工程与爆炸技术', '80.72', '7645', '11', '300', '300', '60.5', '65.8', '培养具备弹药战斗与爆炸技术以及在民用机械工程和工程爆破等方面的基础理论知识和工程实践能力，能在有关科研单位、高等学校、生产企业和管理部门从事系统设计、技术开发、产品制造、实验测试和科技管理方面工作的高级工程技术人才。', '工程制图、理论力学、材料力学、电工与电子技术、弹道学、弹药学、机械设计基础、计算机应用技术', '毕业生可在相关企事业单位从事弹药工程、爆炸与爆破技术等方面的系统设计、技术开发、产品制造、实验测试、技术管理、科研等工作。');
INSERT INTO `major` VALUES ('32', '地面武器机动工程', '79.34', '6756', '11', '400', '300', '87.5', '67.5', '本专业培养具备坦克、装甲车辆、后勤保障车辆和自行火炮为主的地面攻防一体化作战平台及其机动系统等方面的基础理论知识和工程实践能力，能在有关科研单位、高等学校、生产企业和管理部门从事系统设计、技术开发、产品制造、实验测试和科技管理方面工作的高级工程技术人才。', '力学、机械工程、控制科学与工程', '可在国防工业所属的军工企业、科研院所或其他工业部门从事机动武器作战平台、车辆的设计、制造、试验，也可从事普通机械的设计和制造等工作。');
INSERT INTO `major` VALUES ('33', '信息管理与信息系统', '92.54', '6786', '12', '800', '700', '88', '77', '该专业培养具有一定的信息系统和信息资源开发利用实践和研究能力，能够在国家政府部门、企事业单位、科研机构等组织从事信息系统建设与信息管理的复合型高级专门人才。', '管理信息系统、信息资源管理、经济学原理、运筹学、信息系统开发与管理、生产运作与管理、ERP、计算机网络、电子商务等', '党政军机关以及各种企事业单位和金融机构的信息中心、网络管理中心；计算机网络企业、软件企业；各类信息资源开发及咨询机构；相关高等专业教育和科研单位');
INSERT INTO `major` VALUES ('34', '工业工程', '95.68', '7865', '12', '900', '700', '82', '66', '该专业培养具备现代工业工程和系统管理等方面的知识、素质和能力，能在商企业从事生产、经营、服务等管理系统的规划、设计、评价和创新工作的高级专门人才。', '管理学、运筹学、统计、人因工程、机械工程等', '毕业后可在工程、管理、科研和咨询等领域获得广阔的就业机会，能在制作业、服务业、公共事业、科研院所、政府部门和事业单位从事教学、科研、管理及设计开发工作。');
INSERT INTO `major` VALUES ('35', '工程管理', '94.78', '8764', '12', '600', '500', '73', '67.4', '工程管理专业是新兴的工程技术与管理交叉复合性学科。该专业培养具备管理学、经济学、信息工程、土木工程等技术的基本知识，掌握现代管理科学的理论、方法和手段的复合型高级管理人才。', '管理学、土木工程（或水利工程等）\r\n管理学、土木工程（或水利工程等）\r\n管理学、土木工程', '工程管理业的就业领域涉及建筑工程、工程施工和控制管理、房地产经营以及金融、宾馆、贸易等行业部门的管理工作。');
INSERT INTO `major` VALUES ('36', '美术学', '88.24', '5674', '13', '400', '400', '84', '67.4', '美术学专业为美术史论、美术教育领域培养教学和科研、美术评论和编辑、艺术管理和博物馆等方面的高级专门人才', '艺术学、教育学', '毕业后能从事美术教育、美术研究、文博艺术管理、新闻出版等方面的工作');
INSERT INTO `major` VALUES ('37', '艺术设计学', '91.72', '6546', '13', '300', '200', '65', '83.1', '艺术设计学专业培养具备艺术设计学教学和研究等方面的知识和能力，能在艺术设计教育、研究、设计、出版和文博等单位从事艺术设计学教学、研究、编辑等方面工作的专门人才。', '艺术学、历史学、哲学', '广告策划与设计企业、印刷包装企业设计部门、出版社与媒体设计部门从事艺术设计或策划工作。');
INSERT INTO `major` VALUES ('38', '舞蹈学', '93.72', '6754', '13', '500', '400', '69', '82.5', '舞蹈学专业培养具备能从事中外舞蹈史、舞蹈理论的研究、舞蹈教学以及编辑等工作的高等专门人才。本专业培养能在学校、艺术团体、艺术（文化）馆，青少年宫等单位从事朝鲜民族舞蹈、中国民间舞、芭蕾舞的教学、创作、辅导工作的德、智、体全面发展的高级朝鲜民族舞蹈艺术人才和“复合型”专门应用人才。', '艺术学、教育学', '可在文艺演出团体、少年宫、文化宫、群艺馆、艺术院校、中小学、大众事业单位宣传机构从事舞蹈教师、舞蹈指导、艺术体操教练、舞蹈演员等工作。');

-- ----------------------------
-- Table structure for `major_type`
-- ----------------------------
DROP TABLE IF EXISTS `major_type`;
CREATE TABLE `major_type` (
  `major_type_name` varchar(10) NOT NULL,
  `major_type_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`major_type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of major_type
-- ----------------------------
INSERT INTO `major_type` VALUES ('哲学', '1');
INSERT INTO `major_type` VALUES ('经济学', '2');
INSERT INTO `major_type` VALUES ('法学', '3');
INSERT INTO `major_type` VALUES ('教育学', '4');
INSERT INTO `major_type` VALUES ('文学', '5');
INSERT INTO `major_type` VALUES ('历史学', '6');
INSERT INTO `major_type` VALUES ('理学', '7');
INSERT INTO `major_type` VALUES ('工学', '8');
INSERT INTO `major_type` VALUES ('农学', '9');
INSERT INTO `major_type` VALUES ('医学', '10');
INSERT INTO `major_type` VALUES ('军事学', '11');
INSERT INTO `major_type` VALUES ('管理学', '12');
INSERT INTO `major_type` VALUES ('艺术学', '13');

-- ----------------------------
-- Table structure for `need_student`
-- ----------------------------
DROP TABLE IF EXISTS `need_student`;
CREATE TABLE `need_student` (
  `school_id` int(10) unsigned NOT NULL,
  `province_id` int(10) unsigned NOT NULL,
  `time_id` int(10) unsigned NOT NULL,
  `low_grade` varchar(255) DEFAULT NULL,
  `low_rank` int(255) DEFAULT NULL,
  `need_student` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`school_id`,`province_id`,`time_id`),
  KEY `provincee_id` (`province_id`),
  KEY `timee_id` (`time_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of need_student
-- ----------------------------
INSERT INTO `need_student` VALUES ('1', '1', '1', '670', '4000', '5000');
INSERT INTO `need_student` VALUES ('2', '1', '1', '673', '3400', '5000');
INSERT INTO `need_student` VALUES ('3', '7', '1', '560', '45000', '7000');
INSERT INTO `need_student` VALUES ('3', '7', '3', '551', '48000', '7800');
INSERT INTO `need_student` VALUES ('7', '7', '1', '550', '48000', '6000');
INSERT INTO `need_student` VALUES ('7', '7', '2', '545', '48006', '6007');
INSERT INTO `need_student` VALUES ('4', '1', '1', '595', '4104', '41');
INSERT INTO `need_student` VALUES ('4', '2', '1', '582', '5236', '39');
INSERT INTO `need_student` VALUES ('4', '3', '1', '614', '252', '54');
INSERT INTO `need_student` VALUES ('8', '1', '1', '455', '32382', '30');
INSERT INTO `need_student` VALUES ('8', '2', '1', '507', '16012', '41');
INSERT INTO `need_student` VALUES ('8', '3', '1', '480', '15851', '49');
INSERT INTO `need_student` VALUES ('11', '1', '1', '489', '23244', '54');
INSERT INTO `need_student` VALUES ('11', '2', '1', '534', '11407', '23');
INSERT INTO `need_student` VALUES ('11', '3', '1', '521', '7986', '34');
INSERT INTO `need_student` VALUES ('15', '1', '1', '673', '105', '27');
INSERT INTO `need_student` VALUES ('15', '2', '1', '681', '102', '53');
INSERT INTO `need_student` VALUES ('15', '3', '1', '675', '10', '56');
INSERT INTO `need_student` VALUES ('17', '1', '1', '656', '354', '91');
INSERT INTO `need_student` VALUES ('17', '2', '1', '660', '428', '84');
INSERT INTO `need_student` VALUES ('17', '3', '1', '664', '10', '99');
INSERT INTO `need_student` VALUES ('18', '1', '1', '638', '935', '23');
INSERT INTO `need_student` VALUES ('18', '2', '1', '639', '1092', '22');
INSERT INTO `need_student` VALUES ('18', '3', '1', '652', '10', '21');
INSERT INTO `need_student` VALUES ('19', '1', '1', '609', '2797', '62');
INSERT INTO `need_student` VALUES ('19', '2', '1', '601', '3475', '55');
INSERT INTO `need_student` VALUES ('19', '3', '1', '630', '50', '56');
INSERT INTO `need_student` VALUES ('33', '1', '1', '610', '2730', '11');
INSERT INTO `need_student` VALUES ('33', '2', '1', '605', '3152', '9');
INSERT INTO `need_student` VALUES ('33', '3', '1', '618', '169', '12');
INSERT INTO `need_student` VALUES ('34', '1', '1', '561', '8617', '20');
INSERT INTO `need_student` VALUES ('34', '3', '1', '579', '1676', '30');
INSERT INTO `need_student` VALUES ('39', '1', '1', '626', '1588', '23');
INSERT INTO `need_student` VALUES ('39', '2', '1', '630', '1529', '43');
INSERT INTO `need_student` VALUES ('39', '3', '1', '646', '10', '54');
INSERT INTO `need_student` VALUES ('42', '1', '1', '598', '6207', '76');

-- ----------------------------
-- Table structure for `position`
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `position_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `position_name` varchar(255) NOT NULL,
  PRIMARY KEY (`position_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES ('1', '东北');
INSERT INTO `position` VALUES ('2', '华东');
INSERT INTO `position` VALUES ('3', '华北');
INSERT INTO `position` VALUES ('4', '华中');
INSERT INTO `position` VALUES ('5', '华南');
INSERT INTO `position` VALUES ('6', '西南');
INSERT INTO `position` VALUES ('7', '西北');

-- ----------------------------
-- Table structure for `province`
-- ----------------------------
DROP TABLE IF EXISTS `province`;
CREATE TABLE `province` (
  `province_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `province_name` varchar(255) NOT NULL,
  `position_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`province_id`),
  KEY `province_position` (`position_id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of province
-- ----------------------------
INSERT INTO `province` VALUES ('1', '黑龙江省', '1');
INSERT INTO `province` VALUES ('2', '吉林省', '1');
INSERT INTO `province` VALUES ('3', '辽宁省', '1');
INSERT INTO `province` VALUES ('4', '上海市', '2');
INSERT INTO `province` VALUES ('5', '浙江省', '2');
INSERT INTO `province` VALUES ('6', '北京市', '3');
INSERT INTO `province` VALUES ('7', '河北省', '3');
INSERT INTO `province` VALUES ('8', '河南省', '4');
INSERT INTO `province` VALUES ('9', '湖北省', '4');
INSERT INTO `province` VALUES ('10', '广东省', '5');
INSERT INTO `province` VALUES ('11', '海南省', '5');
INSERT INTO `province` VALUES ('12', '重庆市', '6');
INSERT INTO `province` VALUES ('13', '四川省', '6');
INSERT INTO `province` VALUES ('14', '陕西省', '7');
INSERT INTO `province` VALUES ('15', '甘肃省', '7');

-- ----------------------------
-- Table structure for `province_studenttype`
-- ----------------------------
DROP TABLE IF EXISTS `province_studenttype`;
CREATE TABLE `province_studenttype` (
  `province_id` int(10) unsigned NOT NULL,
  `student_type_id` int(10) unsigned NOT NULL,
  `first_gradeline` double NOT NULL,
  `second_gradeline` double NOT NULL,
  `third_gradeline` double NOT NULL,
  `other_gradeline` double NOT NULL,
  PRIMARY KEY (`province_id`,`student_type_id`),
  KEY `province_student2` (`student_type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of province_studenttype
-- ----------------------------
INSERT INTO `province_studenttype` VALUES ('1', '1', '559', '490', '380', '330');
INSERT INTO `province_studenttype` VALUES ('1', '2', '212', '212', '212', '150');
INSERT INTO `province_studenttype` VALUES ('1', '3', '211', '211', '211', '187');
INSERT INTO `province_studenttype` VALUES ('2', '1', '533', '405', '283', '150');
INSERT INTO `province_studenttype` VALUES ('2', '2', '263', '263', '263', '183');
INSERT INTO `province_studenttype` VALUES ('3', '1', '517', '438', '368', '180');
INSERT INTO `province_studenttype` VALUES ('3', '2', '300', '300', '300', '110');
INSERT INTO `province_studenttype` VALUES ('3', '3', '180', '180', '180', '140');
INSERT INTO `province_studenttype` VALUES ('4', '1', '502', '401', '401', '143');
INSERT INTO `province_studenttype` VALUES ('4', '2', '261', '261', '261', '100');
INSERT INTO `province_studenttype` VALUES ('4', '3', '281', '281', '281', '100');
INSERT INTO `province_studenttype` VALUES ('5', '1', '588', '490', '344', '344');
INSERT INTO `province_studenttype` VALUES ('5', '2', '507', '449', '449', '319');
INSERT INTO `province_studenttype` VALUES ('5', '3', '500', '445', '445', '445');
INSERT INTO `province_studenttype` VALUES ('6', '1', '532', '432', '432', '150');
INSERT INTO `province_studenttype` VALUES ('6', '2', '300', '300', '300', '105');
INSERT INTO `province_studenttype` VALUES ('6', '3', '300', '300', '300', '105');
INSERT INTO `province_studenttype` VALUES ('7', '1', '511', '358', '358', '200');
INSERT INTO `province_studenttype` VALUES ('7', '2', '266', '266', '266', '140');
INSERT INTO `province_studenttype` VALUES ('7', '3', '210', '210', '210', '140');
INSERT INTO `province_studenttype` VALUES ('8', '1', '499', '374', '374', '200');
INSERT INTO `province_studenttype` VALUES ('8', '2', '335', '326', '326', '180');
INSERT INTO `province_studenttype` VALUES ('8', '3', '305', '305', '305', '180');
INSERT INTO `province_studenttype` VALUES ('9', '1', '512', '375', '375', '200');
INSERT INTO `province_studenttype` VALUES ('9', '2', '286', '286', '286', '120');
INSERT INTO `province_studenttype` VALUES ('9', '3', '336', '336', '336', '120');
INSERT INTO `province_studenttype` VALUES ('10', '1', '500', '376', '376', '205');
INSERT INTO `province_studenttype` VALUES ('10', '2', '300', '300', '300', '190');
INSERT INTO `province_studenttype` VALUES ('10', '3', '280', '280', '280', '200');
INSERT INTO `province_studenttype` VALUES ('11', '1', '539', '539', '539', '539');
INSERT INTO `province_studenttype` VALUES ('11', '2', '376', '376', '376', '376');
INSERT INTO `province_studenttype` VALUES ('11', '3', '439', '439', '439', '439');
INSERT INTO `province_studenttype` VALUES ('12', '1', '524', '428', '428', '140');
INSERT INTO `province_studenttype` VALUES ('12', '2', '321', '321', '321', '120');
INSERT INTO `province_studenttype` VALUES ('12', '3', '315', '315', '315', '140');
INSERT INTO `province_studenttype` VALUES ('13', '1', '546', '458', '458', '180');
INSERT INTO `province_studenttype` VALUES ('13', '2', '205', '205', '205', '175');
INSERT INTO `province_studenttype` VALUES ('13', '3', '100', '100', '100', '100');
INSERT INTO `province_studenttype` VALUES ('14', '1', '474', '425', '332', '160');
INSERT INTO `province_studenttype` VALUES ('14', '2', '276', '276', '276', '112');
INSERT INTO `province_studenttype` VALUES ('14', '3', '301', '301', '301', '301');
INSERT INTO `province_studenttype` VALUES ('15', '1', '483', '436', '370', '180');
INSERT INTO `province_studenttype` VALUES ('15', '2', '283', '241', '241', '160');
INSERT INTO `province_studenttype` VALUES ('15', '3', '283', '278', '278', '160');

-- ----------------------------
-- Table structure for `question`
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `question_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `question_title` varchar(80) NOT NULL,
  `question_describe` varchar(100) DEFAULT NULL,
  `question_time` datetime NOT NULL,
  `question_img` varchar(1024) NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`question_id`),
  KEY `question_user` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('1', '上大学选什么App(问题题目)', '升学问APP，你值得拥有', '2018-11-26 15:44:03', 'http://img.zcool.cn/community/01c81c5937d3a6a8012193a39a4d90.jpg@2o.jpg', '1');
INSERT INTO `question` VALUES ('2', '怎样选择大学', '孩子上什么样的大学好', '2018-11-26 15:49:59', 'http://img.zcool.cn/community/01c81c5937d3a6a8012193a39a4d90.jpg@2o.jpg', '2');
INSERT INTO `question` VALUES ('3', '大学选择资料推荐', '升学问App内容丰富', '2018-11-26 15:50:56', 'http://5b0988e595225.cdn.sohucs.com/images/20180524/ba57f10d665f48bcaf8a313eaacdd7c3.jpeg', '3');

-- ----------------------------
-- Table structure for `rank`
-- ----------------------------
DROP TABLE IF EXISTS `rank`;
CREATE TABLE `rank` (
  `time_id` int(10) unsigned NOT NULL,
  `province_id` int(10) unsigned NOT NULL,
  `grade` int(10) unsigned NOT NULL,
  `ranking` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`time_id`,`province_id`,`grade`),
  KEY `province_id` (`province_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rank
-- ----------------------------
INSERT INTO `rank` VALUES ('1', '1', '554', '45000');
INSERT INTO `rank` VALUES ('1', '2', '530', '35000');
INSERT INTO `rank` VALUES ('1', '3', '560', '46899');
INSERT INTO `rank` VALUES ('1', '4', '600', '20000');
INSERT INTO `rank` VALUES ('1', '5', '720', '32');
INSERT INTO `rank` VALUES ('1', '6', '450', '107890');
INSERT INTO `rank` VALUES ('2', '11', '500', '56789');
INSERT INTO `rank` VALUES ('3', '14', '320', '140000');
INSERT INTO `rank` VALUES ('2', '1', '554', '9742');
INSERT INTO `rank` VALUES ('3', '1', '554', '13450');
INSERT INTO `rank` VALUES ('2', '2', '550', '9128');
INSERT INTO `rank` VALUES ('3', '2', '550', '1849');
INSERT INTO `rank` VALUES ('2', '3', '457', '19885');
INSERT INTO `rank` VALUES ('2', '3', '510', '33793');
INSERT INTO `rank` VALUES ('2', '3', '515', '31856');
INSERT INTO `rank` VALUES ('2', '3', '503', '36564');
INSERT INTO `rank` VALUES ('3', '3', '684', '79');
INSERT INTO `rank` VALUES ('3', '3', '571', '14577');
INSERT INTO `rank` VALUES ('3', '3', '544', '22175');
INSERT INTO `rank` VALUES ('1', '4', '610', '52');
INSERT INTO `rank` VALUES ('1', '4', '519', '10611');
INSERT INTO `rank` VALUES ('1', '4', '518', '10849');
INSERT INTO `rank` VALUES ('2', '4', '558', '3056');
INSERT INTO `rank` VALUES ('2', '4', '565', '2148');
INSERT INTO `rank` VALUES ('3', '4', '477', '131494');
INSERT INTO `rank` VALUES ('3', '4', '471', '138020');
INSERT INTO `rank` VALUES ('1', '5', '543', '102323');
INSERT INTO `rank` VALUES ('1', '5', '532', '114071');
INSERT INTO `rank` VALUES ('1', '5', '522', '124610');
INSERT INTO `rank` VALUES ('2', '5', '586', '42380');
INSERT INTO `rank` VALUES ('2', '5', '580', '48006');
INSERT INTO `rank` VALUES ('2', '5', '585', '43278');
INSERT INTO `rank` VALUES ('3', '5', '588', '44225');
INSERT INTO `rank` VALUES ('3', '5', '578', '43024');
INSERT INTO `rank` VALUES ('1', '6', '530', '17261');
INSERT INTO `rank` VALUES ('1', '6', '528', '17509');
INSERT INTO `rank` VALUES ('1', '6', '525', '17887');
INSERT INTO `rank` VALUES ('2', '6', '517', '19317');
INSERT INTO `rank` VALUES ('2', '6', '511', '20117');
INSERT INTO `rank` VALUES ('2', '6', '506', '20840');
INSERT INTO `rank` VALUES ('3', '6', '547', '16473');
INSERT INTO `rank` VALUES ('3', '6', '544', '16935');
INSERT INTO `rank` VALUES ('1', '7', '663', '73');
INSERT INTO `rank` VALUES ('1', '7', '534', '15781');
INSERT INTO `rank` VALUES ('1', '7', '530', '17105');
INSERT INTO `rank` VALUES ('2', '7', '515', '16973');
INSERT INTO `rank` VALUES ('2', '7', '514', '17252');
INSERT INTO `rank` VALUES ('2', '7', '510', '18373');
INSERT INTO `rank` VALUES ('3', '7', '552', '50052');
INSERT INTO `rank` VALUES ('3', '7', '551', '32242');
INSERT INTO `rank` VALUES ('1', '8', '583', '25200');
INSERT INTO `rank` VALUES ('1', '8', '609', '12496');
INSERT INTO `rank` VALUES ('2', '8', '577', '3529');
INSERT INTO `rank` VALUES ('2', '8', '580', '3205');
INSERT INTO `rank` VALUES ('3', '8', '523', '70854');
INSERT INTO `rank` VALUES ('3', '8', '522', '71835');
INSERT INTO `rank` VALUES ('1', '9', '554', '13471');
INSERT INTO `rank` VALUES ('1', '9', '517', '22604');
INSERT INTO `rank` VALUES ('1', '9', '370', '67681');
INSERT INTO `rank` VALUES ('2', '9', '511', '37788');
INSERT INTO `rank` VALUES ('2', '9', '552', '20143');
INSERT INTO `rank` VALUES ('2', '9', '551', '20557');
INSERT INTO `rank` VALUES ('3', '9', '571', '23238');
INSERT INTO `rank` VALUES ('3', '9', '578', '20443');
INSERT INTO `rank` VALUES ('3', '9', '569', '24074');
INSERT INTO `rank` VALUES ('1', '10', '554', '15800');
INSERT INTO `rank` VALUES ('1', '10', '551', '17151');
INSERT INTO `rank` VALUES ('1', '10', '547', '19032');
INSERT INTO `rank` VALUES ('2', '10', '504', '51932');
INSERT INTO `rank` VALUES ('3', '10', '544', '33655');
INSERT INTO `rank` VALUES ('1', '11', '641', '3172');
INSERT INTO `rank` VALUES ('1', '11', '642', '3115');
INSERT INTO `rank` VALUES ('2', '11', '606', '5163');
INSERT INTO `rank` VALUES ('2', '11', '605', '5219');
INSERT INTO `rank` VALUES ('2', '11', '600', '5553');
INSERT INTO `rank` VALUES ('3', '11', '673', '3327');
INSERT INTO `rank` VALUES ('3', '11', '668', '3642');
INSERT INTO `rank` VALUES ('3', '11', '666', '3764');
INSERT INTO `rank` VALUES ('1', '12', '512', '43822');
INSERT INTO `rank` VALUES ('1', '12', '515', '42406');
INSERT INTO `rank` VALUES ('2', '12', '570', '2624');
INSERT INTO `rank` VALUES ('2', '12', '560', '3601');
INSERT INTO `rank` VALUES ('3', '12', '551', '24045');
INSERT INTO `rank` VALUES ('3', '12', '544', '26787');
INSERT INTO `rank` VALUES ('1', '13', '587', '2075');
INSERT INTO `rank` VALUES ('1', '13', '581', '2803');
INSERT INTO `rank` VALUES ('1', '13', '576', '3505');
INSERT INTO `rank` VALUES ('2', '13', '505', '80821');
INSERT INTO `rank` VALUES ('2', '13', '509', '76309');
INSERT INTO `rank` VALUES ('2', '13', '503', '83115');
INSERT INTO `rank` VALUES ('3', '13', '530', '70796');
INSERT INTO `rank` VALUES ('3', '13', '538', '62196');
INSERT INTO `rank` VALUES ('1', '14', '614', '404');
INSERT INTO `rank` VALUES ('1', '14', '606', '545');
INSERT INTO `rank` VALUES ('1', '14', '570', '1938');
INSERT INTO `rank` VALUES ('2', '14', '561', '13137');
INSERT INTO `rank` VALUES ('2', '14', '562', '12904');
INSERT INTO `rank` VALUES ('2', '14', '557', '14055');
INSERT INTO `rank` VALUES ('3', '14', '690', '11');
INSERT INTO `rank` VALUES ('1', '15', '530', '1685');
INSERT INTO `rank` VALUES ('1', '15', '515', '2833');
INSERT INTO `rank` VALUES ('1', '15', '495', '5454');
INSERT INTO `rank` VALUES ('2', '15', '568', '9543');
INSERT INTO `rank` VALUES ('2', '15', '539', '23730');
INSERT INTO `rank` VALUES ('2', '15', '566', '10204');

-- ----------------------------
-- Table structure for `school`
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `school_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `school_name` varchar(255) NOT NULL,
  `school_rank` int(11) NOT NULL,
  `city_id` int(10) unsigned NOT NULL,
  `school_img` varchar(255) DEFAULT NULL,
  `school_content` varchar(255) DEFAULT NULL,
  `school_number` varchar(255) DEFAULT NULL,
  `school_type_id` int(10) unsigned NOT NULL,
  `school_best_major` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`school_id`),
  KEY `city_id` (`city_id`),
  KEY `school_type_id` (`school_type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES ('1', '北京大学', '1', '10', 'http://5b0988e595225.cdn.sohucs.com/images/20171223/942f9ab32de14546acc576efab485b64.jpeg', '北京大学（Peking University）简称“北大”，诞生于1898年，初名京师大学堂，是中国近代第一所国立大学，也是最早以“大学”之名创办的学校，其成立标志着中国近代高等教育的开端。北大是中国近代以来唯一以国家最高学府身份创立的学校', '10001', '1', '软件工程');
INSERT INTO `school` VALUES ('2', '清华大学', '2', '10', 'http://5b0988e595225.cdn.sohucs.com/images/20180709/dd548d178512414db6cd72de60b3b4dc.jpeg', '清华大学（Tsinghua University），简称“清华”，由中华人民共和国教育部直属，中央直管副部级建制，位列“211工程”、“985工程”、“世界一流大学和一流学科”，入选“基础学科拔尖学生培养试验计划”、“高等学校创新能力提升计划”', '10003', '2', '数学');
INSERT INTO `school` VALUES ('3', '河北师范大学', '168', '11', 'http://p6.qhimg.com/t0132a7310acb46fb1f.jpg', '河北师范大学（Hebei Normal University）位于河北省会石家庄市，由中华人民共和国教育部与河北省人民政府共建，是一所具有百年历史和光荣传统的省属重点大学，河北省重点支持的国家一流大学建设一层次高校', '10094', '6', '细胞学');
INSERT INTO `school` VALUES ('4', '兰州大学', '98', '21', 'http://img0.imgtn.bdimg.com/it/u=2999182959,3531399367&fm=26&gp=0.jpg', '兰州大学（Lanzhou University）简称“兰大”，是中华人民共和国教育部直属全国重点大学，中央直管副部级建制，由国家国防科技工业局与教育部共建，国家首批”双一流“世界一流大学建设A类高校，”211工程“、”985工程“重点建设高校', '10730', '3', '空乘');
INSERT INTO `school` VALUES ('5', '兰州交通大学 ', '288', '21', 'http://img.ccutu.com/upload/image/20180126/6365256949685448023995665.jpg', '兰州交通大学（Lanzhou Jiaotong University），简称兰交大，位于甘肃省会兰州市，是甘肃省高水平大学建设院校，入选教育部“卓越工程师教育培养计划”、“中西部高校基础能力建设工程”，为教育部本科教学工作水平评估优秀高校', '10732', '4', '交通运输专业');
INSERT INTO `school` VALUES ('6', '辽宁省交通高等专科学校', '500', '5', 'http://www.gaokw.com/uploads/allimg/180807/1-1PPFU1124T.jpg', '辽宁省交通高等专科学校坐落于历史文化名城沈阳，入选国家建设行业技能型紧缺人才培养培训工程、全国首批国家示范性高等职业院校、第二批国家现代学徒制试点单位。1951年建立东北交通学校，1954年6月，更名为交通部沈阳公路工程学校', '11500', '5', '汽车检测与维修技术');
INSERT INTO `school` VALUES ('7', '河北医科大学', '170', '11', 'http://img1.jc001.cn/img/301/1285301/1244600380164.jpg', '河北医科大学（Hebei Medical University），简称“河北医大”，位于河北省省会石家庄市，由河北省人民政府、国家卫生和计划生育委员会和教育部三方共建，入选国家“中西部高校基础能力建设工程”、“卓越医生教育培养计划', '050017', '8', '医药学');
INSERT INTO `school` VALUES ('8', '中国人民解放军国防大学', '20', '3', 'http://www.mxzx123.net/uploads/allimg/180629/7-1P629120K3.jpg', '中国人民解放军国防大学（英文：National Defence University of People\'s Liberation Army，NDU，PLA）是中共中央军委直属大学，副战区级，中国最高军事学府和“2110工程”重点建设院校，为中国军队高级任职教育的一所综合性联合指挥大学。', '16124057', '13', '指挥专业');
INSERT INTO `school` VALUES ('9', '北京艺术传媒职业学院', '708', '10', 'http://img1.imgtn.bdimg.com/it/u=4093663591,2948048338&fm=214&gp=0.jpg', '北京艺术传媒职业学院（原北京新圆明职业学院），位于首都北京市，成立于1994年，是经北京市人民政府批准，教育部备案，具有独立颁发学历文凭资格，全国唯一一所艺术与传媒并列命名的综合性艺术传媒类普通高等学校', '14140', '11', '工商企业管理专业');
INSERT INTO `school` VALUES ('10', '廊坊师范学院', '552', '12', 'http://img.ccutu.com/upload/images/2017-6/20150.jpg', '廊坊师范学院（Langfang Teachers University），建院于1946年，是经教育部批准建立的省属普通高等本科院校，位于京津走廊上的明珠河北省廊坊市，距北京天安门广场40公里，距天津中心区60公里。', '10100', '14', '化学');
INSERT INTO `school` VALUES ('11', '黑龙江大学', '112', '1', 'http://img1.imgtn.bdimg.com/it/u=791826522,3876439721&fm=26&gp=0.jpg', '黑龙江大学（Heilongjiang University），诞生于1941年，其前身为中国人民抗日军政大学第三分校俄文队，是由中国共产党一手创办的我国最早培养俄语高级专门人才的红色大学，教育部、国家国防科技工业局与黑龙江省共建高校，国家首批中西部高校基础能力建设工程高校，黑龙江省“双一流”建设A类高校，入选国家首批卓越法律人才教育培养计划、国家建设高水平大学公派研究生项目、特色重点学科项目、新工科研究与实践项目、国家级人才培养模式创新实验区、国家级特色专业建设点、国家级大学生创新创业训练计划、教育部来华', '10212', '7', '外国语言文学');
INSERT INTO `school` VALUES ('12', '东北石油大学', '214', '2', 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1700279272,3214410211&fm=26&gp=0.jpg', '东北石油大学（Northeast Petroleum University），简称东油（NEPU），坐落于中国最大的石油石化基地黑龙江省大庆市，是伴随中国最大油田大庆油田的发现而诞生的一所全国重点大学，是中国石油天然气集团公司、中国石油化工集团公司、中国海洋石油总公司和黑龙江省人民政府四部共同建设的石油院校，是黑龙江省重点建设的高水平大学，还是国家“卓越工程师教育培养计划”、“国家大学生创新性实验计划”实施高校，国家“特色重点学科项目”建设高校，“国家建设高水平大学公派研究生项目”实施高校', '10220', '7', '石油工程');
INSERT INTO `school` VALUES ('13', '东北电力大学', '184', '4', 'https://www.dxsbb.com/upFiles/infoImg/2015112557975581.jpg', '东北电力大学（Northeast Electric Power University），简称东电（NEEPU），坐落在吉林省吉林市，入选教育部“卓越工程师教育培养计划”、首批“中西部高校基础能力建设工程”重点建设高校。学校为首批国家级工程实践教育中心建设单位，全国社会体育人才培训和科研基地。2016年加盟“全球能源互联网发展合作组织”，同年发起加入“中国智能量测产业技术创新战略联盟”。', '10188', '7', '电气工程及其自动化');
INSERT INTO `school` VALUES ('14', '辽宁科技大学', '324', '6', 'https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/crop%3D0%2C8%2C550%2C363%3Bc0%3Dbaike80%2C5%2C5%2C80%2C26/sign=7515a862356d55fbd1892c6650126372/0d338744ebf81a4c028d2875df2a6059242da6cb.jpg', '辽宁科技大学(University of Science and Technology Liaoning)简称“辽科大”，始建于1948年（戊子年），是新中国最早组建的冶金高校之一。1958年成立本科学院鞍山钢铁学院；2002年经国家教育部批准更名为鞍山科技大学； 2006 年经国家教育部批准更名为辽宁科技大学；同时成为国务院学位委员会批准的具有博士学位授予权单位；是国家首批“卓越工程师教育培养计划2.0”、“新工科研究与实践项目”入选高校，辽宁省一流大学重点建设高校。', '10146', '7', '冶金技术');
INSERT INTO `school` VALUES ('15', '复旦大学', '5', '7', 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=978353,3152870942&fm=26&gp=0.jpg', '复旦大学（Fudan University），简称“复旦”，位于中国上海，由中华人民共和国教育部直属，中央直管副部级建制，国家双一流（A类）、985工程、211工程建设高校，入选珠峰计划、111计划、2011计划、卓越医生教育培养计划、国家建设高水平大学公派研究生项目，九校联盟（C9）、中国大学校长联谊会、东亚研究型大学协会、环太平洋大学协会的重要成员，是一所世界知名、国内顶尖的全国重点大学。 ', '10246', '1', '数学');
INSERT INTO `school` VALUES ('16', '上海交通大学', '6', '7', 'http://pic.baike.soso.com/ugc/baikepic2/60380/20170202072619-526606728.jpg', '上海交通大学（Shanghai Jiao Tong University），简称“上海交大”，位于中国直辖市上海，是中华人民共和国教育部直属并与上海市共建的全国重点大学，是中国历史最悠久、享誉海内外的著名高等学府之一， [1]  位列“985工程”、“211工程”、“世界一流大学建设高校”，入选“珠峰计划”、“111计划”、“2011计划”、“卓越医生教育培养计划”、“卓越法律人才教育培养计划”、“卓越工程师教育培养计划”、“卓越农林人才教育培养计划”，为九校联盟、中国大学校长联谊会、Universita', '10248', '1', '经济学类');
INSERT INTO `school` VALUES ('17', '同济大学', '23', '7', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545638590&di=1df0faf7b2bc12733eecab62e29ddfe3&imgtype=jpg&er=1&src=http%3A%2F%2Fa.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F1f178a82b9014a90be18c279a2773912b21beed4.jpg', '同济大学（Tongji University），简称“同济”，是中华人民共和国教育部直属，由教育部、国家海洋局和上海市共建的全国重点大学，历史悠久、声誉卓著，是国家“双一流”、“211工程”、“985工程”重点建设高校，也是收生标准最严格的中国大学之一；入选2011计划、珠峰计划、卓越工程师教育培养计划、卓越法律人才教育培养计划、卓越医生教育培养计划、111计划、国家大学生创新性实验计划、国家建设高水平大学公派研究生项目、中国政府奖学金来华留学生接收院校、国家级大学生创新创业训练计划、国家级新工科研究与实', '10247', '2', '土木工程');
INSERT INTO `school` VALUES ('18', '华东师范大学', '28', '7', 'http://p0.qhimg.com/t01106b55a873a9f3d3.jpg', '华东师范大学（East China Normal University），简称“华东师大”，位于中国上海，由中华人民共和国教育部直属，位列世界一流大学建设高校、“985工程”、“211工程”，入选国家“2011计划”、“111计划”和“国家建设高水平大学公派研究生项目”，“长三角高校合作联盟”、“金砖国家大学联盟”、“亚太高校书院联盟”、“中日人文交流大学联盟”成员，设有研究生院和国家大学科技园，是教育部、上海市人民政府和国家海洋局共建的全国重点大学', '10269', '1', '心理学');
INSERT INTO `school` VALUES ('19', '华东理工大学', '52', '7', 'http://hiphotos.baidu.com/lbsugc/pic/item/63d9f2d3572c11dfab6fabfb662762d0f603c2af.jpg', '华东理工大学（East China University of Science and Technology）简称“华理”，位于上海市，是中华人民共和国教育部直属的一所具有理工特色，覆盖理、工、农、医、经、管、文、法、艺术、哲学、教育11个学科门类的研究型全国重点大学；国家首批“双一流”世界一流学科建设高校，国家首批“211工程”、“985平台”重点建设高校，高水平行业特色大学优质资源共享联盟成员，入选国家建设高水平大学公派研究生项目、111计划、卓越工程师教育培养计划、2011计划、国家级新工科研究与实', '10251', '1', '化学工程与工艺');
INSERT INTO `school` VALUES ('20', '温州医科大学', '191', '8', 'http://img2.imgtn.bdimg.com/it/u=538842471,1126694993&fm=26&gp=0.jpg', '温州医科大学（Wenzhou Medical University）位于浙江温州，简称温医大，第二批省重点建设高校， [1]  是全国首批硕士学位授予单位，具有博士学位授予权，是国家卫生和计划生育委员会、教育部和浙江省人民政府三方共同建设的高校，入选国家“111计划”、“卓越医生教育培养计划”。', '10343', '7', '临床医学');
INSERT INTO `school` VALUES ('21', '湖州师范学院', '387', '9', 'http://nfile.365zhaosheng.com/Upload/userimg/20140713211689378937.jpg', '湖州师范学院（Huzhou University），简称湖州师院，湖州地区唯一一所本科院校， [1]  位于浙江省湖州市，是一所由湖州市人民政府举办的综合性公办全日制普通高等院校', '10347', '7', '小学教育');
INSERT INTO `school` VALUES ('22', '河南科技大学', '138', '13', 'http://img5.imgtn.bdimg.com/it/u=743739560,3361310715&fm=26&gp=0.jpg', '河南科技大学（Henan University of Science and Technology）简称河科大（HAUST），坐落于“千年帝都，牡丹花城”、中原城市群副中心城市—洛阳，是国家国防科工局与河南省人民政府共建的省属重点大学，属河南省重点建设的三所综合性大学之一，位列中西部高校基础能力建设工程、卓越工程师教育培养计划、卓越医生教育培养计划、卓越农林人才教育培养计划，入选全国深化创新创业教育改革示范高校、国家级专业技术人员继续教育基地，是“新丝绸之路大学联盟”理事高校，是教育部首批认定有条件接收外', '10464', '7', '机器设计制造及其自动化');
INSERT INTO `school` VALUES ('23', '洛阳师范大学', '452', '13', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545641170&di=0f27696eb0a61559bb13568ea8e49c26&imgtype=jpg&er=1&src=http%3A%2F%2Fcrawl.nosdn.127.net%2Ff19c1e4c91bb578f209a5166b5a482ea.jpg', '洛阳师范学院（Luoyang Normal University）是一所省属普通高等师范本科院校，位于千年帝都、牡丹花城、丝路起点——洛阳，为河南省文明单位、河南省普通高校毕业生就业工作先进单位、教育硕士专业学位研究生培养试点单位。', '10482', '7', '汉语言文学');
INSERT INTO `school` VALUES ('24', '洛阳理工学院', '499', '13', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545641191&di=18cfd0ac7e08b03c683308dacc59a4ed&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.fenglincanyin.com%2Fuploads%2Fimages%2F201712%2F1513136275457255.jpg', '洛阳理工学院（Luoyang Institute of Science and Technology），坐落于享有“千年帝都，牡丹花城”之美誉的中国古都——河南省洛阳市，是一所经国家教育部批准成立的公办省属全日制普通本科高等院校，为河南省首批五所转型发展试点院校、教育部本科教学工作合格评估优秀院校。', '11070', '7', '无机非金属材料工程');
INSERT INTO `school` VALUES ('25', '三峡大学', '211', '14', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545641211&di=6bd61b3c6811ed8edbe53659e3ded5dc&imgtype=jpg&er=1&src=http%3A%2F%2Fcdn.applysquare.net%2Fa2%2Finstitute%2Fcn.ctgu%2Fcover_app.png', '三峡大学是经国家教育部批准由原武汉水利电力大学（宜昌）和原湖北三峡学院于2000年5月25日合并组建的，是国家水利部和湖北省共建大学，入选教育部第二批“卓越工程师教育培养计划”、中西部高校基础能力建设工程（二期）、湖北省“国内一流大学”建设高校。', '11075', '7', '水利水电工程');
INSERT INTO `school` VALUES ('26', '佛山科学技术学院', '441', '15', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545046526765&di=1f0ae531a0e37f2f0527e42c28820cc6&imgtype=0&src=http%3A%2F%2Fwww.p5w.net%2Fmoney%2Fzh%2F201805%2FW020180509514853114517.jpg', '佛山科学技术学院（Foshan University），原佛山大学，国家硕士学位授予单位，广东省高水平理工科大学，博士学位授予立项建设单位，教育部数据中国“百校工程”试点院校，是国家“卓越工程师教育培养计划2.0”、“新工科研究与实践项目”入选高校。', '11847', '7', '国际经济与贸易');
INSERT INTO `school` VALUES ('29', '珠海艺术职业学院', '531', '16', 'http://www.gaosan.com/upload/webimg/4101.jpg', '珠海艺术职业学院是经广东省人民政府批准，教育部备案，颁发电子注册文凭，纳入国家统一招生计划的普通高等院校(学院代码：12576)。学院坐落在珠海大道中部金湾区大学园区，依山傍海，环境优美，毗邻港澳，生活方便。。', '12576', '11', '艺术设计专业');
INSERT INTO `school` VALUES ('33', '重庆大学', '31', '18', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545046661886&di=59c16d7614972f5ebabf4b2acf0719b6&imgtype=0&src=http%3A%2F%2Fpic.baike.soso.com%2Fp%2F20131107%2F20131107134729-1416464457.jpg', '重庆大学（Chongqing University），简称重大（CQU），位于中央直辖市重庆，是中共中央直管、教育部直属的31所副部级全国重点大学之一，由教育部和重庆市共同建设。重大早在民国时期就是中国最杰出的国立大学之一，建国后以“建筑老八校”和“电气五虎”闻名。现是国家“双一流”、“211工程”和“985工程”首批重点建设的高水平研究型综合性大学；入选“2011计划”、“111计划”、“卓越工程师教育培养计划”、“卓越法律人才教育培养计划”、“海外高层次人才引进计划”、“中国政府奖学金来华留学生接收院', '10611', '7', '建筑学');
INSERT INTO `school` VALUES ('34', '西南大学', '37', '18', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545046682225&di=190a2da48ee91d248ca4ddd391052a6f&imgtype=0&src=http%3A%2F%2Fimg2.ph.126.net%2FRXU0Mv3oe9A5a43XtEMPJg%3D%3D%2F6632165078303256434.jpg', '西南大学（Southwest University）简称西大，坐落于重庆市，是中华人民共和国教育部直属高校，由教育部、农业部与重庆市人民政府共建，是“双一流”世界一流学科建设高校，位列国家“211工程” 、“985工程优势学科创新平台”，入选“111计划”、“2011计划”、“卓越农林人才教育培养计划”、“卓越教师培养计划”。是开办师范生免费教育的7所高校之一，全国自主选拔录取改革试点高校，中国政府奖学金来华留学生接收院校，国家建设高水平大学公派研究生项目60所合作院校之一，重庆市大学联盟创始学校之一。', '10635', '7', '基础心理学');
INSERT INTO `school` VALUES ('35', '西南政法大学', '126', '18', 'http://5b0988e595225.cdn.sohucs.com/images/20180902/43a880f0f65a47d1b73ce4dfbd1f39b6.jpeg', '西南政法大学（Southwest University of Political Science & Law）简称“西政”，位于中国直辖市重庆，由中华人民共和国教育部与重庆市人民政府共建，是新中国最早建立的政法类高等学府，改革开放后首批全国重点大学，全国首批卓越法律人才教育培养计划基地，中国政府奖学金来华留学生接收院校，国家中西部高校基础能力建设工程、国家建设高水平大学公派研究生项目、国家特色重点学科项目入选高校，自主招生试点高校。学校以法学为主，经济学、文学、管理学、哲学、工学等多学科协调发展，被誉为新', '10652', '7', '法学');
INSERT INTO `school` VALUES ('36', '西南科技大学', '235', '19', 'http://img3.imgtn.bdimg.com/it/u=3324717485,2840155712&fm=26&gp=0.jpg', '西南科技大学（Southwest University of Science and Technology）坐落于中国科技城——四川省绵阳市。学校是四川省人民政府与教育部、国家国防科技工业局共建高校，被教育部确定为国家西部重点建设十四所高校之一，入选四川省“双一流”建设计划、中西部高校基础能力建设工程、卓越工程师教育培养计划2.0、新工科研究与实践项目、教育部&四川省卓越工程师教育培养计划、四川省卓越法律人才教育培养计划、四川省卓越农林人才教育培养计划、四川2011计划。 [1-5]  原中央政治局常委，', '10619', '7', '土木工程');
INSERT INTO `school` VALUES ('38', '咸阳师范学院', '644', '20', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545046774901&di=0798d1ba54325d8ded1ed099fe99c7b3&imgtype=0&src=http%3A%2F%2Fimg1.youzy.cn%2Fuploadfiles%2F2015%2F8%2F31%2Fimage%2F20150831%2F20150831094037_9424.jpg', '学校肇始于1978年5月的“陕西师范大学咸阳专修科”。1978年12月，经教育部批准，成立“咸阳师范专科学校”。2001年5月，与陕西省咸阳教育学院合并，成立咸阳师范学院。2004年10月，陕西广播电视大学咸阳市分校并入。2016年7月，陕西省机电工程学校划归学校', '10722', '7', '数学与应用数学');
INSERT INTO `school` VALUES ('39', '四川大学', '12', '22', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545046820129&di=2347aa29a15f2ae96bc4c9a80d4b5f61&imgtype=0&src=http%3A%2F%2Fcms.qinxue100.com%2Fuploads%2Fallimg%2F1803%2F143-1P3301Z5501N.jpg', '四川大学（Sichuan University），简称“川大”，是中华人民共和国教育部直属的高水平综合性全国重点大学，中央直管副部级建制，世界一流大学建设高校，国家“985工程”、“211工程”重点建设高校，入选国家珠峰计划、2011计划、111计划、卓越工程师教育培养计划、卓越医生教育培养计划、国家大学生创新性实验计划、国家建设高水平大学公派研究生项目、中国政府奖学金来华留学生接收院校、国家级大学生创新创业训练计划、国家级新工科研究与实践项目、全国深化创新创业教育改革示范高校，拥有研究生院和研究生自主划', '10610', '7', '口腔医学');
INSERT INTO `school` VALUES ('40', '电子科技大学', '29', '22', 'http://a1.att.hudong.com/06/83/01200000025518136358830804115.jpg', '电子科技大学（University of Electronic Science and Technology of China）坐落于四川省会成都，直属中华人民共和国教育部，由教育部、工业和信息化部、四川省和成都市共建。位列国家“985工程”、“211工程”、“世界一流大学建设高校”（A类），入选国家“2011计划”、“111计划”、“卓越工程师教育培养计划”、“国家建设高水平大学公派研究生项目”、“中国政府奖学金来华留学生接收院校”，两电一邮成员。是一所完整覆盖整个电子类学科，以电子信息科学技术为核心，', '10235', '3', '通讯工程');
INSERT INTO `school` VALUES ('41', '西南财经大学', '94', '22', 'http://img3.imgtn.bdimg.com/it/u=2551677785,1889507054&fm=26&gp=0.jpg', '西南财经大学（SWUFE）位于四川省会、国家历史文化名城成都市，直属中华人民共和国教育部，国家首批“双一流”世界一流学科建设高校，国家”211工程“、”985工程优势学科创新平台“重点建设高校，入选”2011计划“、国家建设高水平大学公派研究生项目、中国政府奖学金来华留学生接收院校、全国深化创新创业教育改革示范高校；是以经济学管理学为主体、金融学为重点的全国重点大学，被誉为“中国金融人才库”。', '10651', '7', '金融');
INSERT INTO `school` VALUES ('42', '深圳大学', '73', '23', 'http://5b0988e595225.cdn.sohucs.com/images/20180622/9455d800580c4b18b4d8183eac0d7558.jpeg', '深圳大学（Shenzhen University），简称“深大”，位于中国经济特区广东省深圳市，是经国家教育部批准设立，由广东省主管、深圳市人民政府主办的综合性大学，入选广东省高水平大学重点建设高校，为国家大学生文化素质教育基地、全国文明校园、全国首批深化创新创业教育改革示范高校、全国地方高校UOOC联盟发起单位，设有研究生院。具有推荐免试研究生资格。', '10590', '1', '经济学');

-- ----------------------------
-- Table structure for `school_type`
-- ----------------------------
DROP TABLE IF EXISTS `school_type`;
CREATE TABLE `school_type` (
  `school_type_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `school_type_name` varchar(255) NOT NULL,
  PRIMARY KEY (`school_type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of school_type
-- ----------------------------
INSERT INTO `school_type` VALUES ('1', '985');
INSERT INTO `school_type` VALUES ('2', '211');
INSERT INTO `school_type` VALUES ('3', '一本');
INSERT INTO `school_type` VALUES ('4', '二本');
INSERT INTO `school_type` VALUES ('5', '专科');
INSERT INTO `school_type` VALUES ('6', '双一流');
INSERT INTO `school_type` VALUES ('7', '综合');
INSERT INTO `school_type` VALUES ('8', '医药');
INSERT INTO `school_type` VALUES ('9', '政法');
INSERT INTO `school_type` VALUES ('10', '体育');
INSERT INTO `school_type` VALUES ('11', '艺术');
INSERT INTO `school_type` VALUES ('12', '民族');
INSERT INTO `school_type` VALUES ('13', '军事');
INSERT INTO `school_type` VALUES ('14', '师范');
INSERT INTO `school_type` VALUES ('15', '语言');

-- ----------------------------
-- Table structure for `student_type`
-- ----------------------------
DROP TABLE IF EXISTS `student_type`;
CREATE TABLE `student_type` (
  `student__type_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `student_type_name` varchar(255) NOT NULL,
  PRIMARY KEY (`student__type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_type
-- ----------------------------
INSERT INTO `student_type` VALUES ('1', '普通生');
INSERT INTO `student_type` VALUES ('2', '艺术生');
INSERT INTO `student_type` VALUES ('3', '体育生');

-- ----------------------------
-- Table structure for `subject`
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `subject_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(255) NOT NULL,
  PRIMARY KEY (`subject_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES ('1', '语文');
INSERT INTO `subject` VALUES ('2', '数学');
INSERT INTO `subject` VALUES ('3', '英语');
INSERT INTO `subject` VALUES ('4', '历史');
INSERT INTO `subject` VALUES ('5', '地理');
INSERT INTO `subject` VALUES ('6', '政治');
INSERT INTO `subject` VALUES ('7', '物理');
INSERT INTO `subject` VALUES ('8', '化学');
INSERT INTO `subject` VALUES ('9', '生物');
INSERT INTO `subject` VALUES ('10', '艺术');
INSERT INTO `subject` VALUES ('11', '体育');
INSERT INTO `subject` VALUES ('12', '职级');

-- ----------------------------
-- Table structure for `subject_majortype`
-- ----------------------------
DROP TABLE IF EXISTS `subject_majortype`;
CREATE TABLE `subject_majortype` (
  `subject_id` int(10) unsigned NOT NULL,
  `major_type_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`subject_id`,`major_type_id`),
  KEY `subject_type2` (`major_type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subject_majortype
-- ----------------------------
INSERT INTO `subject_majortype` VALUES ('1', '1');
INSERT INTO `subject_majortype` VALUES ('1', '2');
INSERT INTO `subject_majortype` VALUES ('1', '4');
INSERT INTO `subject_majortype` VALUES ('2', '1');
INSERT INTO `subject_majortype` VALUES ('2', '2');
INSERT INTO `subject_majortype` VALUES ('2', '3');
INSERT INTO `subject_majortype` VALUES ('2', '4');
INSERT INTO `subject_majortype` VALUES ('3', '1');
INSERT INTO `subject_majortype` VALUES ('3', '2');
INSERT INTO `subject_majortype` VALUES ('3', '5');
INSERT INTO `subject_majortype` VALUES ('4', '1');
INSERT INTO `subject_majortype` VALUES ('4', '4');
INSERT INTO `subject_majortype` VALUES ('4', '5');
INSERT INTO `subject_majortype` VALUES ('4', '6');
INSERT INTO `subject_majortype` VALUES ('5', '6');
INSERT INTO `subject_majortype` VALUES ('5', '7');
INSERT INTO `subject_majortype` VALUES ('5', '8');
INSERT INTO `subject_majortype` VALUES ('6', '3');
INSERT INTO `subject_majortype` VALUES ('6', '11');
INSERT INTO `subject_majortype` VALUES ('6', '12');
INSERT INTO `subject_majortype` VALUES ('7', '7');
INSERT INTO `subject_majortype` VALUES ('7', '8');
INSERT INTO `subject_majortype` VALUES ('8', '7');
INSERT INTO `subject_majortype` VALUES ('8', '8');
INSERT INTO `subject_majortype` VALUES ('8', '9');
INSERT INTO `subject_majortype` VALUES ('8', '10');
INSERT INTO `subject_majortype` VALUES ('9', '9');
INSERT INTO `subject_majortype` VALUES ('9', '11');
INSERT INTO `subject_majortype` VALUES ('10', '13');
INSERT INTO `subject_majortype` VALUES ('11', '13');
INSERT INTO `subject_majortype` VALUES ('12', '7');
INSERT INTO `subject_majortype` VALUES ('12', '8');
INSERT INTO `subject_majortype` VALUES ('12', '9');

-- ----------------------------
-- Table structure for `time`
-- ----------------------------
DROP TABLE IF EXISTS `time`;
CREATE TABLE `time` (
  `time_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `time_name` varchar(255) NOT NULL,
  PRIMARY KEY (`time_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of time
-- ----------------------------
INSERT INTO `time` VALUES ('1', '2018');
INSERT INTO `time` VALUES ('2', '2017');
INSERT INTO `time` VALUES ('3', '2016');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `user_password` varchar(20) NOT NULL,
  `user_email` varchar(20) DEFAULT NULL,
  `user_sex` varchar(5) NOT NULL,
  `user_tel` varchar(11) NOT NULL,
  `user_img` varchar(10240) DEFAULT NULL,
  `user_balance` double DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'sage', '123abc', '1236746@qq.com', '男', '15226599293', '/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAFA3PEY8MlBGQUZaVVBfeMiCeG5uePWvuZHI////////////////////////////////////////////////////2wBDAVVaWnhpeOuCguv/////////////////////////////////////////////////////////////////////////wAARCACWAJYDASIAAhEBAxEB/8QAGQAAAwEBAQAAAAAAAAAAAAAAAAEDAgQF/8QAJhAAAgICAgICAwADAQAAAAAAAAECEQMhEjFBUQQiEzJhQnGRgf/EABYBAQEBAAAAAAAAAAAAAAAAAAABAv/EABYRAQEBAAAAAAAAAAAAAAAAAAARAf/aAAwDAQACEQMRAD8A4wAAADUYuXQOLi9oKyADIAAGAG4YpzVxVozFcml7O/HFYoK3oDgacXTVNCL/ACPtk5JNL2/JKgLw MnG5S2QnDhNxOmGaPD7WmQyS5zbAmBoQCHHsDUYSa5KLr2AONgaAggCAosbqzSKfHlGMny8j RKMmuJECKywGxAAwABq7VdnQszc4qb qIRWzdbcqdJgdWaUfxNNpt9HNDHKb qMFsbcVaCiMYfjlyvn0jEMbyS4ossbav mYyeKd0BnJ8eUGvNinglCPLTKy QpSSapBLIk0o3KXoIjCSjjknG2 jo/NjWNbppdEc/JtSca0QA3YGUwIMRVyo6uLUUcydOzbyycaNI1kx6TRRxio76JPM6qgnl5RqgLPFGUdHPOPGVBGco9MUm5O2RSGBTFilk60gKfHlGMXy7v0bzTj LjCu90RUJQk4vsJQlCP2VWwMpFY9pDwY7 0ugnxjN8VoK64riqOfOkpaNYsn1oWVJpvyEc/kr8dNqUqFkxqONtN2Zx5Z441HdvoKrlf0fLo58uJ46tp2dM8sJ42nV jmjFybT8AToCnDdAERHRkfJlDY Jmx8mENqhBybNY3Hmuf6kUi2DN PTWiU6Unx/XwdXx8cHjtpOwJyyOeRSr/Q8 bnUUqoo2sceFdvRzS3kaXsKriUpY2k1xb2bclibglaa2ThNwTjHYrc8n23dAdM2nx49USnL/GMXfRqVY9xVIxOUYTUo92QSk5frK/9MrjyOGLULa8iWRPLyyRu/BVxSiuOovsozihL83KUdSKcIx6RPPmpqMf ix5m3Uu2gE1sBt7AI4gACoANRjydBKPF0AgQUUx4nPrS9kV0/jjLFUUr8GMNwlKLbX8K44rHGrs01btdkUS4yStXRPhBvUe 9lbVcWajxrVARhHhdI0oLnz8srX/AFg9Q/8ACpU5q409ijFR33ex8nfWyi8EVCXFupLwO09VorJqOhJpy2BlKKjqKM8IJforKPijNJvQEIJyvT7A6OVdAUebQGuLDiwh43UthN3IXFg0wCki CVXRBFcb/gHUpJ ArfZnGuUjeVUiKOP9FTsnY037A3x23q2Dv2Zt wt wGk07s3UmuyVv2PlL2wNtPzsyo69Gbfs2otoBqNL9v haXkxJUZAo5R9gSsAIAUdDUU12WkTAbVOhFQUjeNLZgtDHLg34IKY1UrRWe4nPtDU37IE0FD5ML/AIFKmA7HYCYh2ABHs6F0c6dG/wArCaMr 2kTocpNsVNhSpAFABzpM0nQAWFAAAKZ04uX4nfXg51jk/DOjFyjGpdBGR0Ot6K8VRBGgoHphYUUFf0AAKCgAAoKQjcFyAzX8ApKDonVdgZA1FJgBFYZvxRtYPbNRn6Nqa8lSMrDFeLNqCXSGmn5NAKhSWjdmZdAS6Ze9EWbUrVMgxK7FsbQIKErKLGjMeyqCam4JGHrwWl0SdgxkrjJodsKrJqiHkb35FRUbxqwCGgIqVcXoaAAugam0ABGozvVFKACoy0ZACKHoAAAHbAAH35EAAKgoAAKQ0AAZnPhWrsAAD//2Q==', '50.32');
INSERT INTO `user` VALUES ('2', 'user2', '123', '12378563@qq.com', '男', '15226599293', 'http://www.17qq.com/img_qqtouxiang/41550424.jpeg\r\n', '90.2');
INSERT INTO `user` VALUES ('3', 'user3', '123', '12378435@163.com', '男', '15226599293', '/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAFA3PEY8MlBGQUZaVVBfeMiCeG5uePWvuZHI////////////////////////////////////////////////////2wBDAVVaWnhpeOuCguv/////////////////////////////////////////////////////////////////////////wAARCACWAJYDASIAAhEBAxEB/8QAGQAAAwEBAQAAAAAAAAAAAAAAAAEDAgQF/8QAJhAAAgICAgICAwADAQAAAAAAAAECEQMhEjFBUQQiEzJhQnGRgf/EABYBAQEBAAAAAAAAAAAAAAAAAAABAv/EABYRAQEBAAAAAAAAAAAAAAAAAAARAf/aAAwDAQACEQMRAD8A4wAAADUYuXQOLi9oKyADIAAGAG4YpzVxVozFcml7O/HFYoK3oDgacXTVNCL/ACPtk5JNL2/JKgLw MnG5S2QnDhNxOmGaPD7WmQyS5zbAmBoQCHHsDUYSa5KLr2AONgaAggCAosbqzSKfHlGMny8j RKMmuJECKywGxAAwABq7VdnQszc4qb qIRWzdbcqdJgdWaUfxNNpt9HNDHKb qMFsbcVaCiMYfjlyvn0jEMbyS4ossbav mYyeKd0BnJ8eUGvNinglCPLTKy QpSSapBLIk0o3KXoIjCSjjknG2 jo/NjWNbppdEc/JtSca0QA3YGUwIMRVyo6uLUUcydOzbyycaNI1kx6TRRxio76JPM6qgnl5RqgLPFGUdHPOPGVBGco9MUm5O2RSGBTFilk60gKfHlGMXy7v0bzTj LjCu90RUJQk4vsJQlCP2VWwMpFY9pDwY7 0ugnxjN8VoK64riqOfOkpaNYsn1oWVJpvyEc/kr8dNqUqFkxqONtN2Zx5Z441HdvoKrlf0fLo58uJ46tp2dM8sJ42nV jmjFybT8AToCnDdAERHRkfJlDY Jmx8mENqhBybNY3Hmuf6kUi2DN PTWiU6Unx/XwdXx8cHjtpOwJyyOeRSr/Q8 bnUUqoo2sceFdvRzS3kaXsKriUpY2k1xb2bclibglaa2ThNwTjHYrc8n23dAdM2nx49USnL/GMXfRqVY9xVIxOUYTUo92QSk5frK/9MrjyOGLULa8iWRPLyyRu/BVxSiuOovsozihL83KUdSKcIx6RPPmpqMf ix5m3Uu2gE1sBt7AI4gACoANRjydBKPF0AgQUUx4nPrS9kV0/jjLFUUr8GMNwlKLbX8K44rHGrs01btdkUS4yStXRPhBvUe 9lbVcWajxrVARhHhdI0oLnz8srX/AFg9Q/8ACpU5q409ijFR33ex8nfWyi8EVCXFupLwO09VorJqOhJpy2BlKKjqKM8IJforKPijNJvQEIJyvT7A6OVdAUebQGuLDiwh43UthN3IXFg0wCki CVXRBFcb/gHUpJ ArfZnGuUjeVUiKOP9FTsnY037A3x23q2Dv2Zt wt wGk07s3UmuyVv2PlL2wNtPzsyo69Gbfs2otoBqNL9v haXkxJUZAo5R9gSsAIAUdDUU12WkTAbVOhFQUjeNLZgtDHLg34IKY1UrRWe4nPtDU37IE0FD5ML/AIFKmA7HYCYh2ABHs6F0c6dG/wArCaMr 2kTocpNsVNhSpAFABzpM0nQAWFAAAKZ04uX4nfXg51jk/DOjFyjGpdBGR0Ot6K8VRBGgoHphYUUFf0AAKCgAAoKQjcFyAzX8ApKDonVdgZA1FJgBFYZvxRtYPbNRn6Nqa8lSMrDFeLNqCXSGmn5NAKhSWjdmZdAS6Ze9EWbUrVMgxK7FsbQIKErKLGjMeyqCam4JGHrwWl0SdgxkrjJodsKrJqiHkb35FRUbxqwCGgIqVcXoaAAugam0ABGozvVFKACoy0ZACKHoAAAHbAAH35EAAKgoAAKQ0AAZnPhWrsAAD//2Q==', '30.34');
INSERT INTO `user` VALUES ('4', 'user4', '123', '54376189@qq.com', '女', '67843658649', '/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDABALDA4MChAODQ4SERATGCgaGBYWGDEjJR0oOjM9PDkzODdASFxOQERXRTc4UG1RV19iZ2hnPk1xeXBkeFxlZ2P/2wBDARESEhgVGC8aGi9jQjhCY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2P/wAARCACWAJYDASIAAhEBAxEB/8QAGgAAAwEBAQEAAAAAAAAAAAAAAAEDAgQGBf/EADEQAAICAQMDAgQGAQUBAAAAAAABAhESAyExE0FRYXEEBRWRIiNCUoGhsTJi0eHwwf/EABgBAQEBAQEAAAAAAAAAAAAAAAABAgQD/8QAGxEBAAMBAQEBAAAAAAAAAAAAAAEREgITA0H/2gAMAwEAAhEDEQA/APNtp12BXXowrb3Gr3rZHWHScXzf9DVqpNbeorvnf3C9tijUab/4HGslkrQlsaxdpPa/JQR 5VK47OnxuyXfY1tf8eCwjd0vxU/5BOmYkm xpXxfHkotF/h25FqK0ku4QfZIuopJPmiiK03VN1XY58ZPWtrJRPpJR/S9/JjpYybV7/2Slc7WVKS3XnuQ1NJRVqUXd8M7NR4RucbfPBzShk21FJL7oSlOeTSapVXfubjK1iolpaSUFVb7Nd/cgsk2t1vwZlQ0lKpL AKOKk7X2AiucKsdXv8AcauN9nwRSGl3EvXg1355KgXI14Eh1W9FD3v jap9uFvuYS3GrCHJ7Gobvzexh78lILx4KL6KylGNpW6tvYpJPTbuUXTatcM520o3dWV0MZy23kvJbG4OTjSXPfg64xqCU20 yJp0vy95L9RTTpyanF35A5PjHktv5s5Yy/0t2vVH0/iNG1 FLdGJfDRjpvvt22I1Tm14aawcNbqSkrlUaS9Djlpu/bwX1U4TqO1be5hyTQJliEnFb7dgFP3AyzbCi7ruKimIUu5GmEu469TSQ8QtMjrevBqgqypRPm/sHY1VjSCDp0k7TtXS5RqMG3SW/oOMJSkopNt7JJXZeKjovepz8cxX/P8Aj3LYmtFKOWo8Y9vL/wDeSi4qsY K/wAif4lcnfawUVwEX0ZRT2W98tl ZKuTmhs1/VHRDeSrktjrhoZRT7eo4aThqU2pJ7V4N6UqglzsEknK5P8A6M21Dk L BT1bgsY8u/J8r4jSWbx2V8npbg1FTfJw/EfDwlrV mO6a4/9wIlZi3wpRa7fwB1/EQhKVxVNPcDTFObEWLLYix3PG3tSWI8SuIY2xZSWJpJ NimNe4Yuy2lMKH2Kaeg5pydKC5k Pb3L9GGgvz1c720 /8APj25MvVc28lFqqjGto yLaTDLnGMXHRTUWqbfMv vT/JhL h0ax29RaUO/8A9Gor QRteS2Ucajs90V07W9E0rKRb7C0p1Q1JY0alKXLOdSktls0UUtt37ktYhRfEx3Uqdd65M9adueP4Xs9zDxV0qsxu4U1uS24hy/EKLnfkDGqt5Wnd89gNWxTOIYlsAxOfTopHEeOxXAMBopKmW09VaME9KNavfUfMfbx7hjsgxGkpJrffkdFMAwLoymo9x4m1EeA0mWK38mkjWI1EumclSvZ u5pLcFGjaVJprnixpMnE2la9DMVwjaQ0lE09q2G4PHyUUfQto4qazWUe6JbUPny01Ld2gO6cIObfbtQDSuHEMC IsTm09qRxDEtiGI0UjiGBbEMS6KSxDEtiGI0UljsGBZRHiXSUjgGJbEMRopNQvsNRXgoojUS6SmFH0NxiaSZpIaSircdb2bSHQ0lMNNgbxAmlpPpg9M6 kHTOXT3px9MOmdfSDpk0U4 mHTOvpB0y6KcmDHgdXTDp g0U5cAwOrph0/QukpzYBgdPTH0xopzKD4HgdHTH0y6Kc6ixqJfp hrp g0lIKJpJlen6GsBpKQx9AOjDYBopbAOmWoKPFdIYB0y1BQXSPTDplqDEGkOmHTL4hQLQ6YdMvQUC0OmHTL0FIFoYD6ZagKlpdMMCoFLTwHgU2AIngBQCls9RC6iPNfW9X/AGfYz9a1v3RX8G/Ht5 vL0 aHmjy6 d6y5cX7oH871n3ivZE8Oz15eozQ80eb0fnsk/zUpLytmdT d/DY3lJvxRmfl3H41HfM/r7OaFmj4Or88jX5cHv3kQXzjWveafuix8e5SfpzD0uaF1Eecj841VzJP3Rr6zLxEvj2enL0HUQPVR576zLxEw/m2o/1L7CPj2enL0fVXkz1kebfzOb5m/uL6nP97NeHSevL0vWXkOsvJ5r6pqfvF9Wmv1Inj0evL03WXkXXXk8z9Xl  LNfV2uUh49L68vSddeQPLS azk/wDXXogL4dJ68vg5zq7Rla7e9AB2TDkHXbdu/ax5zq0 fLACQBSn6fc1nqdmvuAGqgCep5X3GpavFr7gAqAJ6v7l9xvU1e7QAKgsr1JcNA qtskADMFhrW/cvuSerOO7k9gAsxBbMteVr8Toy9d7 gAZoHUm 41Kfn wARATlPyAAKH/2Q==', '80.32');

-- ----------------------------
-- Table structure for `work`
-- ----------------------------
DROP TABLE IF EXISTS `work`;
CREATE TABLE `work` (
  `work_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `work_name` varchar(255) NOT NULL,
  PRIMARY KEY (`work_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of work
-- ----------------------------
INSERT INTO `work` VALUES ('1', '教育');
INSERT INTO `work` VALUES ('2', '金融');
INSERT INTO `work` VALUES ('3', '医药');
INSERT INTO `work` VALUES ('4', '计算机');
INSERT INTO `work` VALUES ('5', '建筑');
INSERT INTO `work` VALUES ('6', '设计');
INSERT INTO `work` VALUES ('7', '政治');

-- ----------------------------
-- Table structure for `work_school`
-- ----------------------------
DROP TABLE IF EXISTS `work_school`;
CREATE TABLE `work_school` (
  `work_id` int(10) unsigned NOT NULL,
  `school_id` int(10) unsigned NOT NULL,
  `salary` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`work_id`,`school_id`),
  KEY `school_id` (`school_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of work_school
-- ----------------------------
INSERT INTO `work_school` VALUES ('1', '1', '80');
INSERT INTO `work_school` VALUES ('2', '3', '60');
INSERT INTO `work_school` VALUES ('3', '6', '75');
INSERT INTO `work_school` VALUES ('4', '4', '74');
INSERT INTO `work_school` VALUES ('5', '7', '64');
INSERT INTO `work_school` VALUES ('6', '8', '45');
INSERT INTO `work_school` VALUES ('7', '2', '78');

-- ----------------------------
-- Table structure for `writer`
-- ----------------------------
DROP TABLE IF EXISTS `writer`;
CREATE TABLE `writer` (
  `writer_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `writer_name` varchar(20) NOT NULL,
  `writer_img` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`writer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of writer
-- ----------------------------
INSERT INTO `writer` VALUES ('1', '盘尼西林', 'http://img1.imgtn.bdimg.com/it/u=415529631,3012801483&fm=214&gp=0.jpg');
INSERT INTO `writer` VALUES ('2', '白砂糖', 'https://b-ssl.duitang.com/uploads/item/201806/05/20180605154742_CLXtP.jpeg');
INSERT INTO `writer` VALUES ('3', '无名氏', 'https://b-ssl.duitang.com/uploads/item/201806/05/20180605154744_aSh2Q.jpeg');
INSERT INTO `writer` VALUES ('4', '张三', 'https://b-ssl.duitang.com/uploads/item/201508/04/20150804162802_NcXfP.png');
