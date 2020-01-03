package com.blade.manager.system.permission.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * TODO:
 *
 * @author blade
 * 2020/1/2 15:31
 */
@Controller
public class RandomController {

    private static String[] names = {};

    private static String[] phonePrefix = {};

    private static String[] addresses = {
            "流沙西街道流沙大道电力局对面",
            "流沙西街道赵厝寮油坊街",
            "流沙西街道锦绣东小区",
            "流沙西街道华景花园",
            "流沙西街道广达南路溪心沟",
            "流沙西街道国土资源局旁",
            "流沙西街道江景新城",
            "流沙西街道锦绣饭店对面",
            "流沙西街道跃进门路",
            "流沙西街道赵厝寮跃进门",
            "流沙西街道赵厝寮拍斗势",
            "流沙西街道赵厝寮晖景街道",
            "流沙西街道明华园正对面",
            "流沙西街道翡翠明珠酒店后面",
            "流沙西街道流沙大道建设局斜对面",
            "流沙西街道启育聋哑中心",
            "流沙西街道流沙大道谷仓顶",
            "流沙西街道培英园中区",
            "流沙西街道培英园北区",
            "流沙西街道文化馆对面",
            "流沙西街道赵厝寮",
            "沙流沙西街道流沙晖景花园小区",
    };

    @RequestMapping("/random")
    public ModelAndView random() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/randomAddress");
        // 姓名  电话  地址
        mv.addObject("name", randomName());
        mv.addObject("phone", randomPhone());
        mv.addObject("address", randomAddress());
        return mv;
    }

    private String randomAddress() {
        String prefix = "广东省普宁市";
        String address = addresses[getRandomSubscript(addresses.length)];
        return prefix + address + getRandomSubscript(99) + "栋" + getRandomSubscript(500) + "号";
    }

    private String randomName() {
        String nameStr = "张吉惟,林国瑞,林玟书,林雅南,江奕云,刘柏宏,阮建安,林子帆,夏志豪,吉茹定,李中冰,黄文隆,谢彦文,傅智翔,洪振霞,刘姿婷,荣姿康,吕致盈,方一强,黎芸贵,郑伊雯,雷进宝,吴美隆,吴心真,王美珠,郭芳天,李雅惠,陈文婷,曹敏侑,王依婷,陈婉璇,吴美玉,蔡依婷,郑昌梦,林家纶,黄丽昆,李育泉,黄芸欢,吴韵如,李肇芬,卢木仲,李成白,方兆玉,刘翊惠,丁汉臻,吴佳瑞,舒绿珮,周白芷,张姿妤,张虹伦,周琼玟,倪怡芳,郭贵妃,杨佩芳,黄文旺,黄盛玫,郑丽青,许智云,张孟涵,李小爱,王恩龙,朱政廷,邓诗涵,陈政倩,吴俊伯,阮馨学,翁惠珠,吴思翰,林佩玲,邓海来,陈翊依,李建智,武淑芬,金雅琪,赖怡宜,黄育霖,张仪湖,王俊民,张诗刚,林慧颖,沈俊君,陈淑妤,李姿伶,高咏钰,黄彦宜,周孟儒,潘欣臻,李祯韵,叶洁启,梁哲宇,黄晓萍,杨雅萍,卢志铭,张茂以,林婉婷,蔡宜芸,林珮瑜,黄柏仪,周逸珮,夏雅惠,王采珮,林孟霖,林竹水,王怡乐,王爱乐,金佳蓉,韩健毓,李士杰,陈萱珍,苏姿婷,张政霖,李志宏,陈素达,陈虹荣,何美玲,李仪琳,张俞幸,黄秋萍,潘吉维,陈智筠,蔡书玮,陈信峰,林培伦,查瑜舜,黎慧萱,郑士易,陈建豪,吴怡婷,徐紫富,张博海,黎宏儒,柯乔喜,胡睿纯,王淑月,陈百菁,王雅娥,黄佩珊,李必辰,吴耀华,彭郁婷,王秀玲,谢佳儒,罗静蓁,杨舒南,蔡政琳,杨绍瑜,金育木,杨韦成,韩宁政,蒋廷湖,毛展霞,廖婉宏,黄怡强,郭冰宇,黄伟依,叶元映,林智超,李姿婷,李莉火,邱雅雯,王淑芳,陈枝盈,高成彦,徐采伶,杨大雪,林彦韦,李升毓,邱宜瑶,陈政文,李宜豪,陈宜宁,陈志宏,阮柔治,林乐妹,简健昀,廖雅君,梁佩芬,苏玮伦,秦娇真,谢佳雯,李仁杰,李佳和,郭贤青,吴怡伶,陈怡婷,阮晴桦,辛翔坤,林孟富,刘美玲,涂昀琬,白凯修,黄蓉芳,赵吟琪,张裕忠,石春紫,方美君,潘右博,俞星如,张冠杰,钟庭玮,叶茜彦,陈伯薇,陈昭祥,陈伟伦,黄雅慧,郭子豪,黄彦霖,宋合,许雅婷,王圣如,何伶元,钟伦军,蔡佳蓉,溥康柔,冯成轩,陈嘉惠,吴惠劭,谢健铭,林怡婷,廖佳蓉,李佩伯,何珮甄,谢晓玲,许彦霖,林威强,周佳勋,林静怡,周筠亚,陈仲宜,胡东贵,陈绍翰,梁姵来,陈雅吉,张莉雯,陈韦荣,林素伦,李菁芷,蔡玉婷,郑智钧,吴孟钰,蔡国伟,连俊达,李雅婷,李礼娇,李忆孝,黄静雯,陈淳宝,李文育,林佳蓉,罗依茂,李淑佩,谢怡君,王美玲,黄慧学,邓幸韵,陈秀琬,许岳平,许爱礼,谢一忠,简志雪,赵若喜,许承翰,姚哲维,苏俊安,郭礼钰,姜佩珊,张鸿信,秦欣瑜,李旺劭,陈怡爱,陈秀德,张佳伶,郑凯婷,郑雅任,黄国妹,林芳江,江骏生,黄儒纯,王培伦,陈蕙侑,蔡宜慧,陈信意,陈惠雯,张琇纶,黄碧仪,陈志文,谢懿富,杨凡靖,蔡秀琴,温惠玲,林宗其,林绍泰,何佳慧,蔡辰纶,王雅雯,叶怡财,冯雅筑,李伦圣,彭正仁,刘小紫,温燕达,刘佳雨,吴婷婷,杨怡君,黄康刚,林辰和,陈世人,吴佩霖,张伟杰,刘友淳,张瑞群,洪紫芬,邓家伟,谢佩任,戎郁文,李治火,林石美,郑雅茜,胡台泰,陈怡盈,阙石意,林盈威,林志嘉,李秀玲,王彦霖,叶惟芷,郑星钰,邱贞伟,姚扬云,涂武盛,王雅顺,唐欣仪,陈政圣,陈育福,吴惠雯,李淑淑,黄莉秋,赖俊军,荆彦璋,白怡均,林姿辛,林雅慧,詹允坚,赖淑珍,吴惠美,李凯婷,林承辰,刘亭宝,宋慧元,连书忠,余仪礼,袁哲仪,杜怡臻,潘孝东,周志合,刘力霞,林钰婷,林怡紫,林俊凯,蔡于纬,蔡雅惠,汪喜祥,陈铭侑,郭子珠,许伦吉,陈佳雨,赖英贤,吴嘉茹,陈永桂,张文宏,唐欣怡,丁绍燕,王雅谕,叶柏宇,王婉萍,王宗清,刘心霖,吴柏廷,陈怡臻,杜士豪,李春勋,黄雅慧,吴乔茂,郑婉如,李育坚,黄静雯,赵一蓉,邱萱俐,周立妹,李宝其,张信豪,李昆霖,陈俊安,林建志,黄韦伶,李美麟,张政达,郑惠玲,柳忠廷,黄美娟,许怡君,吴崇南,邱承芷,叶得梅,陈祯月,杨宛儒,阮肇宪,杨益霞,唐盛人,许平纬,许雅如,林秀绮,刘昌东,张家荣,杨淑君,吴俊民,李彦瑜,李彦文,王崇以,王威全,彭琳以,许志任,陈嘉杰,蔡志远,陈信文,陈思廷,吴家毓,李宜紫,杨毅民,林志平,张坚凤,林明春,戴火劭,傅予名,叶佩璇,陈雅雯,萧宗毅,郭淑媛,刘淑卿,陈雅萍,陈佩木,冯惠玲,吴乃亚,刘欣怡,陈意婷,林明珠,陈淑婷,徐宏亦,李佳德,蔡正信,李淑敏,蒋佳玲,蔡佳明,简淑惠,张雅菱,颜淳奇,刘芳仪,陈俊玮,黄雯坚,侯文贤,郑雅雯,黄雅俐,陈婉婷,郑智杰,林玉信,阮侑美,潘怡婷,黄世祥,张韦水,黄彦慈,张峻香,宋轩天,周妤军,江佩玲,陈美芝,张伦启,陈敏爱,杨毓娇,谢姿君,赖姿泰,黄圣映,林柏燕,黄馨香,叶于玟,陈山茹,魏得凤,张明翰,林冠强,李文荣,龚静雯,陈伟孝,刘信俊,李美治,徐景隆,刘怡洁,陈钰梦,谢静宜,戴惠如,王香君,钟汉馨,郑国玮,张哲亚,詹南勋,潘秋福,黄奕君,郭琬婷,冯家华,吴佩仁,周思颖,张柏钧,吴世伟,朱佳琪,陈宗馨,黄菁坚,郑建泉,许金志,平信宏,蔡佳麟,杨佳宏,陈皓雅,吴翊意,张佩珊,温欣桦,王诗铭,许宜辰,林孟哲,黄善宇,王怡贵,许淑玫,张学玉,黄美珍,陈佳心,宋其琪,陈致昀,王建福,刘莹睿,陈正莲,冯萱雨,金淑敏,宋廷意,吴承华,陈家莲,王志源,张佩君,黄志伟,吴孟吟,蔡玮玉,张心怡,吴志绿,何武霖,杨中惟,王佑诚,徐承财,温伟伦,萧郁婷,陈世伟,林敬书,谢孟南,项宜真,崔美珠,赖筱雯,陈盛丰,阮升云,周怡季,江佳芸,郭惠君,吕智文,王琼龙,陈姿茜,张佩君,李佩纬,黄珮心,李旭友,王健以,蔡幸弘,刘智钧,张佳颖,郭东欣,黄志忠,李娇郁,许欣怡,杜劭依,荣美君,陈翠昆,陈昆绍,黄志佩,黄舜文,刘丽卿,张倩纬,施珮昆,郑纬水,林明玫,郑琦希,童家贤,郭峻豪,林雅豪,曲祯妹,林冠宇,陈珍莹,简玟君,杜阳吟,许雅菱,叶木书,黄文其,陈景彦,蒋承凯,黄依萍,黄珮秋,郑文贤,柯元燕,李思雄,王绍玉,金尧珊,李政绍,叶法盛,苏琪山,邱思妤,王若芳,黎彦君,冷翊瑜,张明真,李琪源,林素侑,孟敏宇,怀雅惠,吴士弘,张世志,袁志铭,林千惠,宋乐男,黄淑贞,王宗盛,吴永臻,陈富财,钱孟儒,谢百宜,曹振豪,陈品妃,吴升财,陈昀雪,高淑娟,林智钧,林秋平,王汉季,黄仁添,詹雅如,蔡佳麟,张美君,孙姿吟,陈孟善,邓威育,谢博容,邓宗毅,陈伟芷,林原隆,骆修秋,李宜喜,张雅婷,许志紫,陈子杰,杨隆志,李淑芬,李惠文,徐治蓉,陈季苹,陈佳霖,赖和慈,李怡伶,林育来,李宛仪,高启淑,吴碧沛,蔡宗奇,刘惠雯,欧郁雯,陈孟伟,黄良财,张介玟,诸智湖,陈雅惠,林华昀,刘慧惟,赖碧仲,温哲湖,赖文豪,王怡奇,陈韵惠,郭胜治,梁佳慧,梁晓雯,陈育珍,梁筱行,黄宗筠,阮紫瑄,韩晓轩,弓文欣,周淑贞,李骏轩,黄俊颖,黄建中,吴惠文,许梅天,周丽珠,夏宏达,郭湖睿,韩雅雯,张伟智,詹萱贵,刘冠宇,曾宛真,吴惠仪,韩孟儒,李家铭,赖哲荣,陈建纬,林威绿,许佩桦,吴欣星,刘育亦,黄国星,童淑玲,刘伟伦,毛佳琳,杨淑花,蒋姿吟,金佑诚,高旺盈,吴雅玲,林蕙和,王均智,黄玉婷,王胜杰,黄惠祥,林家青,赖维乐,蔡翠希,蒋大鸿,童百良,刘克怡,陈玉睿,谢合海,简江瑶,程琬婷,何怡如,李雅雯,钟雅雯,于思秋,王裕仁,陈永秀,陈惠如,黄美君,林盈秀,王姿雯,王旻峰,郭淑卿,谢盈冰,魏淑华,刘志宜,张怡婷,梁淑敏,王白木,方劭中,田火劭,叶佩正,程巧欢,陈玮弘,张毅琳,吴念筠,张美惠,陈欣白,李怡文,曹珊贵,梁家豪,洪月瑄,李铭茹,金杰蕙,杨冠霖,吴明宜,陆怡雯,黄立启,林雅芸,林佩毓,李宜珊,钱怡伶,刘宗翰,林台斌,杨杰均,连育如,杨奕名,郭玉婷,王翊谦,彭圣杰,郑世杰,吴承翰,张琦芸,邓淑芬,陈佳芳,吴秋良,陈舜文,蔡靖玮,林雅舜,张真勇,邱益勇,林懿淳,郭美慧,蔡智强,张智苹,陈冰茹,李思纬,郑馨仪,程明辉,陈弘善,陈政勋,卢琬杰,黄柏菁,刘佩儒,陈韦贞,许台法,陶志祥,吴俊宁,李孟岳,刘明宪,吴耀英,陈思桦,井弘坤,黄明萍,陈姿颖,张成欢,张燕佳,李雪鸿,叶志伟,王石竹,孙心桂,舒大钧,林秋萍,柯美惠,姜上宪,林欣淳,郭雅娟,宋采信,韩贞仪,李育羽,冯玟泰,林姿儒,蔡敏坤,林惠玲,陈珮义,林书祯,黄政霖,谢姿卿,李爱轩,陈秀纬,蔡思妤,黄伟成,陈怡君,林庭玮,钱瑞钰,罗嘉慧,李仲伦,曾家盈,曹雅萍,黄佳菁,金淳恩,陈士豪,茹耀靖,陈展礼,杜家玮,李晋丰,周晴意,左智超,陈明安,谢翊美,王淑贞,韩威任,刘淑岳,骆沛柔,张韦雪,吴慈山,王子智,陈思香,黄美玲,周雯轩,王彦维,杨惠佳,李千惠,郭刚男,林伟翔,张书松,李昀柔,钱慧敏,黄蕙昀,陈彦达,邓怡雯,胡伟智,陈琪瑶,王成祯,袁辛茂,丁淑辉,白柏青,谢佳瑞,蔡宗雯,詹昭云,黄淑华,李丽礼,许贵宜,蔡慧娟,江丽娟,刘育冰,何家莹,黄慧玲,王淑勇,吴亦扬,吴佳书,祖冠珠,卢俊嘉,郭志铭,罗扬妃,杨淑华,黄妤伟,孙维芬,叶宜洁,陈信俐,谢琬婷,陈筱杰,周隆季,涂慧君,陈怡君,赖韵英,杜定鸿,王淑梦,吴伟侑,萧芳仪,陈筠智,侯百宜,王淑娟,陈欣江,吴胜杰,黄宜君,罗智钧,刘淑娟,李慈泉,林莉婷,丁容定,吴建豪,潘于恭,赖怡伶,詹佳颖,傅姿蓉,沈洁铭,蔡文雯,王凯婷,张咏修,辛雅萍,袁贤欢,柯冠良,潘静宜,黄明哲,程俊宪,陈宗颖,陈玮郁,吴佩琪,陈馨薇,陈萱虹,谢雅惠,杨雅康,简志旺,吕木云,宋湘婷,张玮俊,王姿吟,郑伟芳,王文杰,王芸茜,黄柏幸,罗芸纶,曾欣怡,陆怡萱,黄千慈,袁静如,张昆元,陈采勇,马荣真,刘湘舜,曹智强,许雅玲,王彦儒,林国芸,陈雅慧,钱群秋,游思妤,庾雅婷,洪协慧,林宜蓁,简淑芬,李文彬,何义秀,游石如,陈睿佩,葛真珍,陈俊茂,谢湖慧,陈俊铭,张芳坚,陈冠廷,叶静宜,汤筠霞,洪思贤,骆文馨,江淑玲,邓仪绍,林兰瑄,丁俊毅,白山贵,陈胜火,郭法美,王雅云,黄台育,王嘉琪,胡钰雯,刘培宁,林俊贤,邓淳筠,李汉霖,杨秋雯,蔡登木,敖秀娟,陈家弘,苏承松,陈盈昆,谈亭美,王怡祯,叶胜群,叶志鸿,黄郁翔,柯意孝,杨莉倩,吕孟儒,蔡佩圣,阮伦淑,童启光,王淑惠,张淑智,林正平,陈家伟,廖秋隆,陈俊全,邱天月,黄智荣,李昆文,张钧幸,林怡桦,朱予义,陈亭任,黎雅雯,马立山,黄莉绿,郑惠颖,刘冠宏,林雅婷,陈伟义,卢宝云,陈俊泉,吴容侑,王怡以,林志杰,张雅茹,苏映均,童郁雯,明治羽,李美安,赵彦儒,曹静如,林筱映,陈志峰,冯博文,黄玉喜,王冠杰,洪文君,沈泰菱,黄启峰,詹梅水,蔡嘉山,谢淑真,刁佑正,郑伊筠,林怡如,陈贞仪,连凯琳,骆秀珮,夏佳琪,王彦霖,李宗宪,林杰松,蔡宗原,蔡志行,陈淳丰,汪坚人,赖婉君,黄姿芬,叶素菱,陈弘隆,魏雅芳,李兰任,杨士龙,李佳颖,郑伟哲,陈俊豪,施毅海,周怡梅,邬木绮,张韵如,柯尧萍,林冠儒,李淑惠,林启名,洪淑婷,王士豪,张瑞盈,黄文彬,吴与雄,陈芸伦,陈静轩,刘雅莲,林妙坤,李纯贤,林冠旭,苏翰萱,刘佳玉,张国华,陈静贞,梁柏翔,张玉燕,洪坤瑞,刘均瑜,蔡嘉梅,游良洁,蔡宜齐,阚星钰,徐木辰,邱承颖,吴怡萱,陈宜祥,孙治木,林哲茹,游郁涵,刘宛君,林政哲,谢耀德,刘芳仪,邓恭信,何玉华,林威宇,曾宛臻,张碧志,陈均天,陈宜意,林正霖,吴维冰,詹婉心,张维松,柯惠如,林皇辰,姜怡伶,陈文君,崔学山,谢秉勋,张冰旺,庄惠宏,苏美玲,蒋如君,曾阳谕,黄正宁,叶秋绮,林珍瑶,林兰亚,吴台卿,徐嘉俊,张家宁,陈婉友,吕秀美,陈博亦,刘纯蕙,崔振杰,何乔友,邓金良,赖建琪,谢宛俐,谢文天,刘玮恭,李敬琴,许启花,陈哲玮,王志维,王依昀,林雅安,黄小嘉,吴彦君,王柔靖,陈坚顺,潘心盛,黄启仲,林春任,黄俊铭,杨宗翰,汪慧君,刘佑泰,张睿群,吴宜江,林军辰,袁静怡,张品治,李珮心,郑崇海,许俊来,杜得齐,叶庭玮,惠协发,谢泰平,王淑娟,陈正茜,吴雅玲,林维哲,刘政勋,赖秉竹,曹祯仁,邱芸婷,杨俊霖,钱芝湖,陈碧天,彭君丰,张家文,李雅筑,杨凯珠,蓝紫启,陈静维,陈佳信,徐真宜,詹梦顺,林琦桂,陈致希,谢台念,张珮瑜,吴月淳,林文欣,郭睿纬,江佳原,张景昌,许静雯,潘怡孜,刘雅琪,蔡怡君,陈惠君,冉佳琪,蒙孟涵,魏柏伦,李俊映,陈雅惠,张淳珠,蔡豪羽,郭子扬,吴彦文,陈淑媛,程建宏,叶宝珍,许志霖,许欣怡,邓翰辰,陈建彰,陈庆麟,谢惠雯,林晓薇,林木康,黄沛文,刘育轩,张欣桦,李惠茹,汪康馨,程鸿珊,王吉泰,陈文豪,童毓纶,王丽刚,黄秀雄,陈怡婷,林白珊,洪宗翰,王登康,杨俊龙,杜家瑜,汪泰云,蒋维茜,杨宗翰,苏碧绮,倪建辉,袁俊宏,林平琴,赵文宏,林慧齐,许秀珍,陈美慧,王涵阳,李珮卿,王志伟,袁富毓,黄建依,黄坚源,刘惠萍,郑雅萍,谢欢岳,白柏宇,杨彦君,张家新,王秀天,何信希,曹智仁,张枝兰,钟荣旭,江维伦,嵇信宏,郭依仪,张梦兰,黄易湖,陈雯欢,邓柏毅,童香皓,黄允强,羊淑玲,翁雅馨,蔡雅盛,刘雅惠,牛佩珊,许伦孝,陈美珍,邓静宜,刘颖柔,谢永宇,晏美云,陈圣凯,陈郁仲,王映如,吴文君,刘盈甄,林欣喜,郭勋容,毛怡君,王轩豪,卢淑芬,张瑞桂,赖家欣,张伶强,王嘉岳,黄沛杰,李育诚,苏国维,张维喜,赵如彦,张琪,王文治,谢仕荣,邓念珊,郑志翔,黄怡伯,陈彦正,许文菱,黄介桂,周紫来,邱伟婷,孙冠儒,刘淑娟,钟佩桦,梁雅文,高思妤,陈美娟,颜志嘉,邱俊威,林姵菱,黄世齐,杨坚琳,沈忆劭,傅思颖,王忠来,王建仪,李姿妤,李秀慧,林承翰,王吉茂,项家玮,白静芳,曾惠雯,杨皓书,吴文燕,许雅芳,毛隆蕙,张素欢,杨美君,林立强,李胜杰,林俊宏,萧俊德,陆又梦,蒋湘梦,吕淑美,吴必蓉,张香君,李俊廷,许佳霖,谢孟珠,郑镇宇,刘雅雯,王亦柔,李易英,刘萱俐,连克欢,吴雅惠,袁涵雄,蔡宜新,季志安,叶刚俐,潘豪桂,陈均莲,赵丽美,王仪谕,陈雅茹,杨德法,赖妙俐,林诗雅,黄士荣,吴明筠,谢仁琦,梁伟伦,张晴明,简志智,吴雅柏,赖雅惠,刘建宇,黄柏俊,方家瑜,季昆达,黄玉绍,李盈君,葛建宏,林孟茜,谢财贞,蔡士玮,龙淑慧,陈信旭,郭学竹,黄秋萍,章宜静,王嘉豪,叶志远,林美惠,陈雅婷,陈士铭,陈家杰,白姿育,李平杰,林淑慧,谢馨慧,卢晴德,李岳宁,彭旻幸,毛冠宇,蔡采恩,张惠任,朱千蕙,林镇茂,杨孟儒,吴柏宜,蔡宜江,黄彦铭,林秋谦,陈淑彦,林建宏,黄佳昀,陈中书,陈映士,潘嘉玲,郭南季,林家毓,陈孟芬,周珍天,卫启欢,刘丽颖,吴刚睿,赖美玲,李建弘,陈佑贤,林映舜,林惠学,汪盈甄,郦哲宇,林凯翔,廖婉宏,林柏康,程佩君,郑俊颖,李淑和,蔡毅霞,黄正萍,林惠婷,谢雅晴,蔡军羽,徐文宏,林孟英,蔡原秀,谢亭君,蒋泓美,涂正伟,方君豪,李美君,黄雅婷,林冠学,朱玟秀,蔡咏欣,陈伯白,陈思一,王诗平,潘莹轩,林宛莹,李志贤,邱健江,谢佳琳,周勇映,黄明韦,王宗芝,薛木凡,杨惠珍,张冠伶,吴慧合,许孟涵,陈彦学,邱俊淳,李允郁,杨升海,张与信,周宏伟,高孟君,韩淑芬,张金善,陈美君,刘依海,林宗绿,黄启尧,赖益谦,张士铭,方语念,韩俊宏,柳韦伶,陈立绮,周佳欣,林瑞惠,蔡宛蓉,刘思洁,王俊纶,王郁书,林建成,王思达,吕玟嘉,陈添杰,许巧智,白春茂,郭珊绿,吴逸绿,杨家桂,钱宗坤,黄琬学,陈韵伟,张凯任,李俊宪,巫凯翔,黄雅芳,黄佳琪,王怡君,杨侑伟,王瑞杰,王立定,郑铭淑,李美铭,王介乔,周明生,王佑诚,李文华,曾丽芬,张伶月,王静怡,戴佳慧,程博仪,房皇帆,陈明信,黄子发,张圣福,李佩君,刘郁婷,罗玮婷,陈盈轩,沈永萱,黄琼亚,王豪贵,吴佩芳,陈慧明,陈玲俊,赖阳强,赖儒奇,袁纬萍,杨菁学,钱重谕,徐嘉男,陈正康,徐冠宏,许家铭,吴珊斌,蓝琬婷,郑哲玮,林祯龙,陈淑季,林家奇,王智凯,黄冠宇,李耀中,郑奕豪,崔佳东,吴怡昌,赖裕翔,张克清,陈明哲,吴宛佩,林吉富,吴惠凡,陈耀德,郑耀礼,杨佳旭,沈介新,戴怡忠,张中纯,吴劭智,黄俊娟,郭欣瑜,牛静怡,杜秀娟,萧劭花,林佳珠,翟秀玲,黎任乔,张雅慧,刘家升,陈淑枝,古智仁,黄孟勋,许哲盈,吴欣民,陈秀祥,杨珮珊,杭翰真,林哲铭,张珮莲,赖妙祯,柴裕苹,刘怡安,陈宥俊,张哲铭,林侑轩,林思宏,郭秉奇,张哲琇,魏轩豪,郑惠豪,黄人裕,宋翔容,黄士新,陈雅玲,侯嘉侑,吴佩彦,陈嘉勋,姚冠宇,涂明全,陈健茜,黎怡文,刘家隆,韩晓萍,王力依,羊育廷,林筱婷,林诗涵,林彦霖,沈皓孝,王凤以,黄美慧,杜莉婷,姜盈秀,倪佳玲,李怡欣,林建宏,张心怡,林彦心,郭泰合,林旭芷,林丽弘,王翔顺,柯一尧,王泰伦,卢淑如,林丽淑,张馨士,卢佳玲,许宗颖,刘嘉慧,艾俊达,林春玲,杜智杰,刘泓欣,陈东奇,陈怡友,王菁娥,王修行,张晓玲,谢瑜爱,张雅男,赵雅君,竺德星,陈绮梅,叶耀祯,白玉玲,洪国祥,杨淑博,王玉霖,陈信瑶,夏勋怡,杨佑雅,陈嘉宜,郑芝劭,赖怡云,李正乔,王士杰,林镇宇,杨呈祥,黄佳靖,陈伟霞,黄郁仲,陈圣怡,卢怡君,黄平乐,郭慧萍,蔡任秋,赖桂刚,郭哲嘉,李育源,鞠淑敏,傅俊安,黄郁泰,黄伟均,郑丽韵,周志杰,李文蓁,李姿苹,黄心贞,彭志宇,陈佳蓉,黄彦民,赖雅岚,杨惠雯,李雅绿,洪哲宇,韩俊豪,吴雅婷,许宜芳,陈宛惟,聂睿海,巩家铭,吴新韵,林慧佳,吴文男,陈玉玲,林宜霞,林淑惠,吴馨白,唐承颖,许志峰,陈玉如,吕家庆,卢玉玲,童宜蓁,林育珊,杨淑辉,陈姵军,鲁冠宇,张智强,黄文意,李莉雯,许翊强,蔡雅惠,彭姵倩,黄淑君,卢原士,林俊原,陈韵发,赵佳颖,王淳紫,陈文男,钟逸治,杜惠雯,童杰绿,李雅青,许右轩,张孟颖,杨泓欣,黄贞仪,杨淑帆,林淑齐,曾以天,郭玮茂,黄钰名,蓝恩芳,何秀慧,钱佩芸,焦俊宏,张珮瑜,苏宪皓,张思颖,李美泉,李俊伟,吴品弘,傅玉玟,谢珊宪,林士易,余欣勋,黄冠伶,蔡明宏,王美惠,吕上枝,杨健豪,李嘉明,宋珮华,陈冠行,郭庆清,刘彦文,蒋琦贵,张俞成,易珮如,陈昭仪,吴彦儒,蔡浩英,张致远,杨美韵,傅希伦,林佑瑜,柳左恩,张天康,白淑芬,陈俊杰,张美君,黄嘉慧,张顺木,魏静怡,温宇轩,陈彦志,郭美娟,陈雅玲,李筱映,潘泓旺,孙洁瑄,尚晓强,陈依芝,黄佩玲,林于婷,张智钧,陈文彬,沈宝明,林郁萍,卞佑芸,王欣怡,林志群,吴进淑,黄俊任,黄秀峰,柳坤绿,林瑜宣,刘子惟,陈佳静,林志新,苏秀柔,叶弘月,杨桂清,陈金昀,胡财东,林政辛,张乔易,张婉瑜,刘湘火,杨凯翔,蔡阿妹,陈世竹,刘雅惠,刘素齐,黄秋燕,方肇俊,刘千康,叶怡伶,吕碧杰,张嘉湖,陈香伦,梁吉旭,林万辰,寇欢宣,蔡哲荣,张俊佑,宋恭婷,李奕辰,周贤士,夏淑珍,黄晴珮,杨心怡,王筱群,刘盈君,萧建中,奚立桦,吴奕君,林明菱,刘石新,周佳琇,阮旭麟,谢耀法,郑伊财,方怡璇,吴淑媛,孙依婷,杨琬升,孙馨慧,李宜欣,李欣怡,郑馨仪,黄茹冰,黄建宏,袁馨仁,吴春韵,钱希康,黄峻维,韩美慧,张绮星,吴东侑,冯怡如,谢俊颖,周钧平,陈莹洁,苏维映,许羽花,陈心芸,陈俊燕,沈长行,袁依婷,王筱虹,黄盈如,朱子翔,吴琳伟,皇甫芳仪,简宇贞,支燕政,杜品杰,郑昱顺,林易诚,赖儒威,陈春孝,钱靖琪,曾佑东,王任辉,文真玫,崔惠瑜,钱威廷,黎轩学,杨长璇,蔡定妤,竺冠宇,胡忆书,许昌沛,彭尚杰,冯家齐,林欣颖,杜于易,冯夙元,侯秉勋,杨奇真,黄丽易,吴秋燕,吴政尧,吴嘉舜,李佩芳,黄珮瑜,许彦霖,韩宗颖,吕宜婷,周礼依,吴孟花,王淑慧,倪俊毅,李佩财,林惠雯,吴婉瑜,蔡贞仪,张志贞,黄琬婷,刘华法,蔡绍绮,吴怡英,林意卿,王乔龙,刘均芳,林崇苹,林良杰,王梅海,李欣峰,陈裕俐,杨雅其,黄信春,张翠廷,邓俞文,蔡孟劭,敖冠勋,张雅正,吴雅吉,张芷勇,周淑慧,廖婉强,林政天,吴美惠,林辛慈,吴千光,林如宣,曾尧心,颜岳威,林宜臻,黄惠如,何敬念,杨睿名,张怡君,吕国荣,王士凯,林淑娟,黎士杰,蔡亚妤,张静雯,陆毅昀,黄俊铭,李婉茂,黄志玟,魏婉斌,黄洁玫,骆伦春,陈伟亚,许芳喜,蔡孟吉,李于婷,赖圣杰,林建生,杨雅雯,刘怡发,张琇辉,黄淑娟,叶维生,金竣木,张亭康,魏信宏,张康宏,林国容,林豪清,胡振宇,刘琼麟,林郁荣,杜汉湖,张淑萍,杨希香,钱惠婷,周凯东,蔡伊容,倪秀琴,王雪玲,刘晓雯,李原光,吴佩韦,陈威以,黎佩珊,黎长娇,黄伟贞,陈亭贞,袁裕治,李钰婷,赖宏达,黄宗翰,张智均,赖雅茹,姚凯婷,陈韦廷,葛信瑶,刘建纬,傅馨仪,谢惠萱,刘建来,龙政峰,许晏木,蓝珮如,赖乃倩,叶阳筠,陈嘉慧,陈雅竹,杨佳蓉,林立妃,郑雅竹,李宗芸,林常韦,贲万维,郭恭凤,蔡万睿,吴辛苹,刘欣怡,蔡家豪,甘彦君,黄建华,李怡辉,连芸以,卢姿水,詹家玮,林以惟,郑协荣,马登琴,林佑民,谢明盛,李奕君,赖政宏,周雯珮,冯妤士,许军菁,蔡承法,宋俊毅,傅怡君,林莹贞,吴盈君,周庭玮,段希柏,赖采绿,李正元,林义心,黄维峰,赖一妹,刘育紫,孙琳莲,蔡依洁,张珊云,叶易霞,白子蓉,张祥蕙,张明璇,谢乃文,刘菁一,杨佳莹,杨淑治,钱燕良,姜贤喜,张泓木,张欣桦,简登妃,陈景恭,赖燕琪,杨丽英,王雅奇,郑添中,陈珮吉,傅紫玲,李百海,杨轩豪,林玮玲,袁敬舜,苏妤男,林俊安,杨家祥,黄馨慧,徐诗麟,温政达,王怡珮,涂芳坤,陈秉莹,李彦男,陈佳儒,张玉松,高涵中,吴裕美,李丰荣,李建铭,黄惠珊,黄冠桂,陈明宜,李珮华,李俊花,张玉凤,黄俊坤,谢凤泰,暴琪娟,林子欣,郭华卿,王政宪,李怡圣,杜鸿儒,简景恩,蔡政妃,陈家伟,郭志宇,张嘉绍,刘晓恭,陈如筠,谢景蓁,林郁文,许韵福,袁冠廷,叶淑娟,黄淑琴,杨智钧,陈慧德,张善水,林玮华,陈嘉亚,何浩诚,张健铭,郑家铭,陈秀玲,毕俊尧,李尧佳,赖振玮,詹威如,郑淑威,陈盈妃,寇慧萍,徐姿妤,冯紫儒,曹欣怡,毛萱豪,夏又娇,杨惠昌,黄柏亚,王彦志,艾旺骏,陈伟铭,冯欢秋,林信旭,家惠君,吴慧发,王慧玲,黄彦仲,郑佳秀,吴淑芳,邱建宏,蔡佩裕,林丽维,李希名,赵佩玲,冯怡君,林承龙,吕天茹,蔡佩珊,黄雅博,李柏惟,陈宥峰,毛成坚,刘家友,王君琬,许丽华,崔凤宜,张雅筑,蔡明宏,苏金梅,金喜火,陈嘉慧,吴家佑,林升军,黄俊倩,孙坚政,沈美玉,陈幼雪,陈凯正,王俞吟,李佑郁,刘淑美,萧志成,王嘉文,黄昱宁,李政霞,黄志雄,郑彦智,杜志伟,杨佳舜,刘美玲,林合羽,林与伟,郑俊成,赖志文,张昆苹,袁承翰,赵家玮,张茂峰,张婉如,李雅婷,黄秀辉,林雅茹,张怡伶,陈淑琴,张淑伦,袁宗翰,谢奕妹,蒋世昌,余纬绮,陈珍舜,李佳蓉,谢婉婷,洪儒云,刘雅君,黄镇安,刘雅芳,林忠翰,蔡秀娟,许淑贞,冯瑞亚,赖伊茂,吴家良,陈玉凤,林嘉亦,曹雅晴,杨子翔,郑家铭,杨雅雯,李宏火,陈光心,赖伟铭,路宛贤,黄美靖,邱枝廷,翁启来,陈欣睿,李佩凡,蒋淑慧,赵文君,方芸紫,袁大钧,蔡必玲,陈雅琪,闵家贤,张淑娟,黄俊和,王香恭,黄泓隆,吴明淑,黄彦绮,韩以桦,陈筱海,许彦君,沈君祯,廖家宁,洪淑俐,吴洁虹,洪倩珠,陆欣怡,黄政哲,蔡孟琬,吴信宏,张育菁,姜家荣,许儒吟,童家玮,王哲豪,张贞慧,张慧敏,齐亚绮,高婉君,洪彦廷,蔡惠婷,张韵念,吴雅惠,陈佳友,杨裕仁,李淑君,谢伊婷,林佩泰,曾俊杰,崔佩伟,黄佳玲,李茹民,张慈瑄,谢文伯,童逸淳,张家萱,陈逸凡,洪怡萍,林俊冰,黄馨士,陈钰倩,王亭祥,张哲龙,张文勇,童雅琪,郭初新,蔡茜贵,黄慧吉,阮琳谦,王昱圣,蔡宜珮,郭常群,阮素珮,白英琴,林欣惟,吴旻源,陈雨喜,刘立宏,徐平威,林冠儒,邱雅筑,夏尚苹,张雅志,黄仲峰,蔡哲玮,蔡政诚,李宜希,连思婷,郑凯钧,林淑伶,强石柏,张淑英,巴火瑶,许其凯,蔡政皓,王志靖,陈佳玉,林淑卿,林婷婷,戴右儒,朱诗涵,曹雅惠,郭雅苹,林诗涵,周文婷,姚逸妃,林哲一,蓝佳琪,黄彦博,游雅文,傅岳琳,夏宜静,梁政峰,李淑琦,林淑娟,戴淑婷,吴旻云,李冠光,倪武盛,陈惠雯,谢佳纯,陈玉玲,陈珮青,颜幸志,曹依正,杨芸智,林玉亦,谢伊希,许湖娥,简建廷,林筠宏,黄添圣,许淑华,郭星元,李秀泉,刘志明,廖佳琪,李宁原,张进圣,叶婷玮,伍明辉,张博育,王懿善,陈雅云,张佳琪,周文蕙,蔡靖雯,林子瑶,郭青伟,林佩君,庄雅光,吴雯芸,谢颖梅,张嘉慧,周益义,卢怡君,黄家弘,简彦儒,陈雅茹,杨素贞,杨豪柔,韩轩豪,郭俊德,蔡淑依,陈于珊,黄文星,吴佑诚,林乃文,张初蕙,曹喜木";

        if (names.length == 0) {
            names = StringUtils.split(nameStr, ",");
        }

        return names[getRandomSubscript(names.length)];
    }

    private String randomPhone() {

        if (phonePrefix.length == 0) {
            String[] phonePrefixs = {"1300526",
                    "1300526",
                    "1300527",
                    "1301669",
                    "1302526",
                    "1302527",
                    "1304923",
                    "1304924",
                    "1304925",
                    "1304926",
                    "1304927",
                    "1304928",
                    "1304929",
                    "1305844",
                    "1305845",
                    "1306055",
                    "1306056",
                    "1306057",
                    "1306058",
                    "1306059",
                    "1307650",
                    "1307651",
                    "1307652",
                    "1307653",
                    "1307654",
                    "1307655",
                    "1307656",
                    "1307657",
                    "1307658",
                    "1307659",
                    "1310693",
                    "1310694",
                    "1311210",
                    "1311211",
                    "1311212",
                    "1311213",
                    "1311214",
                    "1311215",
                    "1311216",
                    "1311217",
                    "1311218",
                    "1311219",
                    "1312830",
                    "1312831",
                    "1312832",
                    "1312833",
                    "1312834",
                    "1312835",
                    "1312836",
                    "1312837",
                    "1312838",
                    "1312839",
                    "1314407",
                    "1314408",
                    "1314409",
                    "1316925",
                    "1316926",
                    "1316927",
                    "1316928",
                    "1316929",
                    "1317280",
                    "1317281",
                    "1317282",
                    "1317283",
                    "1317284",
                    "1318480",
                    "1318481",
                    "1318482",
                    "1318483",
                    "1318484",
                    "1319290",
                    "1319291",
                    "1319292",
                    "1319293",
                    "1319294",
                    "1319295",
                    "1319296",
                    "1319297",
                    "1319298",
                    "1319299",
                    "1320220",
                    "1320221",
                    "1320222",
                    "1320223",
                    "1320224",
                    "1320255",
                    "1320256",
                    "1320257",
                    "1320258",
                    "1320259",
                    "1322615",
                    "1322616",
                    "1322617",
                    "1322618",
                    "1322619",
                    "1322917",
                    "1322918",
                    "1322919",
                    "1324225",
                    "1324226",
                    "1324227",
                    "1324228",
                    "1324229",
                    "1324690",
                    "1324691",
                    "1325044",
                    "1325045",
                    "1325066",
                    "1328810",
                    "1328811",
                    "1328850",
                    "1328851",
                    "1328852",
                    "1328853",
                    "1328854",
                    "1328855",
                    "1328856",
                    "1328857",
                    "1328858",
                    "1328859",
                    "1330275",
                    "1330276",
                    "1331817",
                    "1331818",
                    "1331819",
                    "1332275",
                    "1332276",
                    "1335270",
                    "1335271",
                    "1335272",
                    "1335273",
                    "1335274",
                    "1336038",
                    "1336039",
                    "1336078",
                    "1336079",
                    "1337651",
                    "1337652",
                    "1337653",
                    "1337654",
                    "1337663",
                    "1338055",
                    "1338056",
                    "1338057",
                    "1338058",
                    "1338059",
                    "1341390",
                    "1341391",
                    "1341392",
                    "1341393",
                    "1341394",
                    "1341395",
                    "1341396",
                    "1341397",
                    "1341398",
                    "1341399",
                    "1341480",
                    "1341481",
                    "1341482",
                    "1341483",
                    "1341484",
                    "1341760",
                    "1341761",
                    "1341762",
                    "1341763",
                    "1341764",
                    "1341765",
                    "1341766",
                    "1341767",
                    "1341768",
                    "1341769",
                    "1342110",
                    "1342111",
                    "1342112",
                    "1342113",
                    "1342114",
                    "1342115",
                    "1342116",
                    "1342117",
                    "1342118",
                    "1342119",
                    "1343000",
                    "1343001",
                    "1343002",
                    "1343003",
                    "1343004",
                    "1343005",
                    "1343006",
                    "1343007",
                    "1343008",
                    "1343009",
                    "1343490",
                    "1343491",
                    "1343492",
                    "1343493",
                    "1343494",
                    "1343495",
                    "1343496",
                    "1343497",
                    "1343498",
                    "1343499",
                    "1348030",
                    "1348031",
                    "1348032",
                    "1348033",
                    "1348034",
                    "1350015",
                    "1350016",
                    "1350143",
                    "1350144",
                    "1350251",
                    "1350256",
                    "1350260",
                    "1350265",
                    "1350266",
                    "1350267",
                    "1350268",
                    "1350269",
                    "1350903",
                    "1350904",
                    "1353190",
                    "1353191",
                    "1353192",
                    "1353193",
                    "1353194",
                    "1353195",
                    "1353196",
                    "1353197",
                    "1353198",
                    "1353199",
                    "1353450",
                    "1353451",
                    "1353452",
                    "1353453",
                    "1353454",
                    "1353925",
                    "1353926",
                    "1353927",
                    "1353928",
                    "1353929",
                    "1354220",
                    "1354221",
                    "1354222",
                    "1354223",
                    "1354224",
                    "1354390",
                    "1354391",
                    "1354392",
                    "1354393",
                    "1354394",
                    "1354395",
                    "1354396",
                    "1354397",
                    "1354398",
                    "1354399",
                    "1358015",
                    "1358016",
                    "1358017",
                    "1358018",
                    "1358019",
                    "1358020",
                    "1358021",
                    "1358022",
                    "1358023",
                    "1358024",
                    "1358025",
                    "1358026",
                    "1358027",
                    "1358028",
                    "1358029",
                    "1359290",
                    "1359291",
                    "1359292",
                    "1359293",
                    "1359294",
                    "1360011",
                    "1361242",
                    "1361243",
                    "1362026",
                    "1362027",
                    "1362028",
                    "1362029",
                    "1362273",
                    "1364035",
                    "1364036",
                    "1364037",
                    "1364038",
                    "1364229",
                    "1364246",
                    "1364247",
                    "1365295",
                    "1365296",
                    "1365297",
                    "1367055",
                    "1367056",
                    "1367057",
                    "1367058",
                    "1367059",
                    "1368270",
                    "1368271",
                    "1368272",
                    "1368273",
                    "1368274",
                    "1368275",
                    "1368276",
                    "1368277",
                    "1368278",
                    "1368279",
                    "1368280",
                    "1368281",
                    "1368282",
                    "1368283",
                    "1368284",
                    "1368745",
                    "1368746",
                    "1368747",
                    "1368748",
                    "1368749",
                    "1369510",
                    "1369511",
                    "1369512",
                    "1369513",
                    "1369514",
                    "1372930",
                    "1372931",
                    "1372932",
                    "1372933",
                    "1372934",
                    "1372935",
                    "1372936",
                    "1372937",
                    "1372938",
                    "1372939",
                    "1372940",
                    "1372941",
                    "1372942",
                    "1372943",
                    "1372944",
                    "1372945",
                    "1372946",
                    "1372947",
                    "1372948",
                    "1372949",
                    "1375165",
                    "1375166",
                    "1375167",
                    "1375168",
                    "1375169",
                    "1376055",
                    "1376056",
                    "1376057",
                    "1376058",
                    "1376059",
                    "1380231",
                    "1380232",
                    "1382200",
                    "1382201",
                    "1382202",
                    "1382203",
                    "1382204",
                    "1382205",
                    "1382206",
                    "1382207",
                    "1382208",
                    "1382209",
                    "1382290",
                    "1382291",
                    "1382292",
                    "1382293",
                    "1382294",
                    "1382295",
                    "1382296",
                    "1382297",
                    "1382298",
                    "1382299",
                    "1382810",
                    "1382811",
                    "1382812",
                    "1382813",
                    "1382814",
                    "1382815",
                    "1382816",
                    "1382817",
                    "1382818",
                    "1382819",
                    "1390275",
                    "1390276",
                    "1390308",
                    "1392267",
                    "1392268",
                    "1392353",
                    "1392354",
                    "1392355",
                    "1392356",
                    "1392443",
                    "1392444",
                    "1392560",
                    "1392561",
                    "1392562",
                    "1392563",
                    "1392564",
                    "1392565",
                    "1392566",
                    "1392567",
                    "1392568",
                    "1392569",
                    "1392700",
                    "1392701",
                    "1392702",
                    "1392703",
                    "1392704",
                    "1392705",
                    "1392706",
                    "1392707",
                    "1392708",
                    "1392709",
                    "1501447",
                    "1501448",
                    "1501458",
                    "1501459",
                    "1501652",
                    "1501653",
                    "1501654",
                    "1501655",
                    "1501656",
                    "1501657",
                    "1501821",
                    "1501822",
                    "1501823",
                    "1501824",
                    "1501825",
                    "1501826",
                    "1501827",
                    "1501828",
                    "1501829",
                    "1508935",
                    "1508936",
                    "1508937",
                    "1508938",
                    "1508939",
                    "1508944",
                    "1509983",
                    "1511375",
                    "1511376",
                    "1511377",
                    "1511890",
                    "1511939",
                    "1521860",
                    "1521861",
                    "1521862",
                    "1521863",
                    "1521864",
                    "1521865",
                    "1521866",
                    "1521867",
                    "1521868",
                    "1521869",
                    "1521936",
                    "1521937",
                    "1521938",
                    "1521960",
                    "1521961",
                    "1521962",
                    "1521963",
                    "1530250",
                    "1530251",
                    "1530252",
                    "1530253",
                    "1530254",
                    "1530255",
                    "1530256",
                    "1530257",
                    "1530258",
                    "1530259",
                    "1532320",
                    "1532321",
                    "1532322",
                    "1534750",
                    "1534751",
                    "1536120",
                    "1536121",
                    "1536122",
                    "1536123",
                    "1536124",
                    "1560275",
                    "1560276",
                    "1560308",
                    "1562267",
                    "1562268",
                    "1562518",
                    "1562519",
                    "1562560",
                    "1562561",
                    "1562562",
                    "1562563",
                    "1562564",
                    "1562565",
                    "1562566",
                    "1562567",
                    "1562568",
                    "1562569",
                    "1562700",
                    "1562701",
                    "1562702",
                    "1562703",
                    "1562704",
                    "1562705",
                    "1562706",
                    "1562707",
                    "1562708",
                    "1562709",
                    "1566026",
                    "1566027",
                    "1569765",
                    "1571837",
                    "1572413",
                    "1572831",
                    "1572882",
                    "1580767",
                    "1581350",
                    "1581351",
                    "1581352",
                    "1581353",
                    "1581354",
                    "1581355",
                    "1581356",
                    "1581357",
                    "1581358",
                    "1581359",
                    "1581954",
                    "1581955",
                    "1581956",
                    "1581957",
                    "1581958",
                    "1581959",
                    "1581960",
                    "1581961",
                    "1581962",
                    "1581963",
                    "1581964",
                    "1581965",
                    "1581966",
                    "1581967",
                    "1581968",
                    "1581969",
                    "1587519",
                    "1588910",
                    "1588911",
                    "1588912",
                    "1588913",
                    "1588914",
                    "1588915",
                    "1588916",
                    "1588917",
                    "1588918",
                    "1588919",
                    "1588980",
                    "1591493",
                    "1591494",
                    "1591560",
                    "1591561",
                    "1591562",
                    "1591563",
                    "1591564",
                    "1591565",
                    "1591566",
                    "1591567",
                    "1591568",
                    "1591569",
                    "1591719",
                    "1591720",
                    "1591721",
                    "1591722",
                    "1591795",
                    "1591796",
                    "1591797",
                    "1591798",
                    "1591799",
                    "1597510",
                    "1597511",
                    "1597512",
                    "1597513",
                    "1597514",
                    "1597515",
                    "1597516",
                    "1597517",
                    "1597518",
                    "1597519",
                    "1597520",
                    "1597521",
                    "1597522",
                    "1597523",
                    "1597524",
                    "1598690",
                    "1598691",
                    "1599250",
                    "1599251",
                    "1599252",
                    "1599253",
                    "1599254",
                    "1599255",
                    "1599256",
                    "1599257",
                    "1599258",
                    "1599259",
                    "1820663",
                    "1821823",
                    "1821831",
                    "1821862",
                    "1821915",
                    "1860765",
                    "1866630",
                    "1866631",
                    "1866632",
                    "1866633",
                    "1866634",
                    "1867591",
                    "1868804",
                    "1868806",
                    "1870754",
                    "1870768",
                    "1871807",
                    "1871823",
                    "1871826",
                    "1871828",
                    "1871845",
                    "1871846",
                    "1871860",
                    "1871861",
                    "1871862",
                    "1871899",
                    "1880663",
                    "1882290",
                    "1882291",
                    "1882292",
                    "1882293",
                    "1882294",
                    "1882353",
                    "1882354",
                    "1882355",
                    "1882356",
                    "1882443",
                    "1882444",
                    "1882565",
                    "1882566",
                    "1882567",
                    "1882568",
                    "1882569",
                    "1890275",
                    "1890276",
                    "1890308",
                    "1892267",
                    "1892268",
                    "1892353",
                    "1892354",
                    "1892355",
                    "1892356",
                    "1892444",
                    "1892560",
                    "1892561",
                    "1892562",
                    "1892563",
                    "1892564",
                    "1892565",
                    "1892566",
                    "1892567",
                    "1892568",
                    "1892569",
                    "1892705",
                    "1892706",
                    "1892707",
                    "1892708",
                    "1892709",
                    "1893311",
                    "1893312",
                    "1893383",
                    "1893384",
                    "1893385",
                    "1893834",
                    "1894836",
                    "1894837",
                    "1894838",
                    "1899825",
                    "1899826",
                    "1899827"};
            phonePrefix = phonePrefixs;
        }

        String prefix = phonePrefix[getRandomSubscript(phonePrefix.length)];
        int last = getLastPhone((int) (Math.random() * 10000));

        return prefix + last;
    }

    private int getLastPhone(int last) {
        if (last < 1000) {
            last = getLastPhone(last * 10);
        }

        return last;
    }

    private int getRandomSubscript(int length) {
        return (int) (Math.random() * length);
    }
}
