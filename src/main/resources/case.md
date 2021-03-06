# 库存管理系统

### V2.0.0 最终版

#### 南京大学16级软件学院软工二第二组

**用例列表**

---
**目录**
- [库存管理系统](#%E5%BA%93%E5%AD%98%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F)
    - [一、 更新历史](#%E4%B8%80%E3%80%81-%E6%9B%B4%E6%96%B0%E5%8E%86%E5%8F%B2)
    - [二、引言](#%E4%BA%8C%E3%80%81%E5%BC%95%E8%A8%80)
        - [1.1 目的](#11-%E7%9B%AE%E7%9A%84)
        - [1.2 阅读说明](#12-%E9%98%85%E8%AF%BB%E8%AF%B4%E6%98%8E)
        - [1.3 参考文献](#13-%E5%8F%82%E8%80%83%E6%96%87%E7%8C%AE)
    - [三、用例列表](#%E4%B8%89%E3%80%81%E7%94%A8%E4%BE%8B%E5%88%97%E8%A1%A8)
    - [四、用例图](#%E5%9B%9B%E3%80%81%E7%94%A8%E4%BE%8B%E5%9B%BE)
    - [五、详细用例描述](#%E4%BA%94%E3%80%81%E8%AF%A6%E7%BB%86%E7%94%A8%E4%BE%8B%E6%8F%8F%E8%BF%B0)
        - [用例1：商品信息管理](#%E7%94%A8%E4%BE%8B1%EF%BC%9A%E5%95%86%E5%93%81%E4%BF%A1%E6%81%AF%E7%AE%A1%E7%90%86)
        - [用例2：库存查看](#%E7%94%A8%E4%BE%8B2%EF%BC%9A%E5%BA%93%E5%AD%98%E6%9F%A5%E7%9C%8B)
        - [用例3：库存盘点](#%E7%94%A8%E4%BE%8B3%EF%BC%9A%E5%BA%93%E5%AD%98%E7%9B%98%E7%82%B9)
        - [用例4：库存管理报单](#%E7%94%A8%E4%BE%8B4%EF%BC%9A%E5%BA%93%E5%AD%98%E7%AE%A1%E7%90%86%E6%8A%A5%E5%8D%95)
        - [用例5：库存报警单](#%E7%94%A8%E4%BE%8B5%EF%BC%9A%E5%BA%93%E5%AD%98%E6%8A%A5%E8%AD%A6%E5%8D%95)
        - [用例6：客户管理](#%E7%94%A8%E4%BE%8B6%EF%BC%9A%E5%AE%A2%E6%88%B7%E7%AE%A1%E7%90%86)
        - [用例7：制定进货单](#%E7%94%A8%E4%BE%8B7%EF%BC%9A%E5%88%B6%E5%AE%9A%E8%BF%9B%E8%B4%A7%E5%8D%95)
        - [用例8：制定销售单](#%E7%94%A8%E4%BE%8B8%EF%BC%9A%E5%88%B6%E5%AE%9A%E9%94%80%E5%94%AE%E5%8D%95)
        - [用例9：制定进货退货单](#%E7%94%A8%E4%BE%8B9%EF%BC%9A%E5%88%B6%E5%AE%9A%E8%BF%9B%E8%B4%A7%E9%80%80%E8%B4%A7%E5%8D%95)

## 一、 更新历史

<table>
   <tr>
      <td>更新日期</td>
      <td>更新内容</td>
   </tr>
   <tr>
      <td>17/09/17</td>
      <td>创建用例列表</td>
   </tr>
   <tr>
      <td>17/09/20</td>
      <td>完成详细用例描述，优化用例列表
      </td>
   </tr>
   <tr>
      <td>17/09/23</td>
      <td>优化详细用例描述，扩充用例个数至22个
   </tr>
    <tr>
      <td>17/09/24</td>
      <td>修正了几个用例描述
   </tr>
    <tr>
      <td>17/10/12</td>
      <td>修改了非法输入的表述
   </tr>
    <tr>
      <td>17/11/14</td>
      <td>调整了单据的正常流程与扩展流程
   </tr>
    <tr>
      <td>18/01/12</td>
      <td>整合了最终版
   </tr>
</table>

## 二、引言
### 1.1 目的
本文档描述了库存管理系统的用户需求
### 1.2 阅读说明
用例描述的模板参考《软件开发的技术基础卷二》
### 1.3 参考文献
UML2.0 语法，IEEE标准
## 三、用例列表

<table>
   <tr>
      <td>参与者</td>
      <td>用例</td>
   </tr>
   <tr>
      <td>库存管理人员</td>
      <td>1.商品信息管理<br/>2.库存查看<br/>3.库存盘点<br/>4.库存管理报单<br/>5.库存报警单</td>
   </tr>
   <tr>
      <td>进货销售人员</td>
      <td>
      6.客户管理<br/>
      7.制定进货单<br/>
      8.制定销售单<br/>
      9.制定进货退货单<br/>
      10.制定销售退货单<br/>
      </td>
   </tr>
   <tr>
      <td>财务人员</td>
      <td>
      11.制定付款单<br/>
      12.制定收款单<br/>
      13.银行账户管理<br/>
      14.制定现金费用单<br/>
      15.查看经营历程表<br/>
      16.查看经营状况表<br/>
      17.查看销售明细表<br/>
      18.期初建账</td>
   </tr>
   <tr>
      <td>总经理</td>
      <td>
      15.查看经营历程表<br/>
      16.查看经营状况表<br/>
      17.查看销售明细表<br/>
      19.审批单据<br/>
      20.制定销售策略</td>
   </tr>
   </tr>
    <td>其余</td>
    <td>
    21.消息查看<br/>
    22.用户管理<br/>
   </tr>
</table>

## 四、用例图
## 五、详细用例描述
- **ID**
    - 1
- **名称**
    - 商品信息管理
- **创建者**
    - 温曜铭
- **修改者**
    - 温曜铭
- **创建日期**
    - 17/09/09
- **修改日期**
    - 17/11/03
- **参与者**
    - 库存管理人员，目的是完善商品相关信息，管理商品分类与具体商品
- **触发条件**
    - 无
- **前置条件**
    - 仓库管理人员身份被识别并授权
- **后置条件**
    - 更新商品信息
- **优先级**
    - 高
- **正常流程**
    - 1.0 增加对象
        - 1.用户进入待添加对象的目录选择添加商品或添加商品分类的操作
        - 2.系统返回信息填写表格
            - a)需要填写的商品分类信息：
                - 名称（必选）
            - b)需要填写的商品信息：
                - 警戒值（默认值为10）
                - 名称（必填）
                - 型号（必填）
                - 初始库存数量（选填，缺省为0）
                - 进价（必填，默认为50）
                - 零售价（必填，默认为100）
        - 3.用户输入商品分类或商品的相关信息后选择提交选项
        - 4.系统增加商品分类或商品信息并显示更新信息
    - 1.1 删除对象
        - 1.用户选中要删除的对象选择删除（当对象刚创建出来创建信息有误并且没有与别的模块产生交互的情况下可以被删除，交互包括与单据产生关联，商品分类中已添加了第一个对象，一旦产生了交互，例如在销售单上出现则不允许删除。）
        - 2.系统返回“是否确认删除，一旦删除不可更改”
        - 3.用户确认删除
        - 4.系统更新信息并显示更新后信息
    - 1.2 修改对象
        - 1.用户选中要修改的对象后选择修改
        - 2.系统返回编辑表格，其中可修改的字段为商品分类的名称，商品的名称、型号、进价、零售价、警戒值
        - 3.用户进行编辑后选择提交选项
        - 4.系统更新信息并显示更新信息
    - 1.3 查看商品分类
        - 1.用户选中商品分类选择查看
        - 2.系统返回该分类的所有子项目
    - 1.4 查看商品
        - 1.用户选中商品选择查看
        - 2.系统返回商品的名称、编号、型号、库存数量、进价、零售价、警戒值、最近进价、最近零售价
        - 3.用户选择返回上一级界面
        - 4.系统返回上一级界面
    - 1.5 关键字查询
        - 1.用户通过编号、名称、型号查询商品
        - 2.系统返回商品的名称、编号、型号、库存数量、进价、零售价、警戒值、最近进价、最近零售价
        - 3.用户选择返回上一级界面
        - 4.系统返回上一级界面


- **扩展流程**
    - 1.0.1a 第一次添加
        - 1.用户第一次选择添加操作
        - 2.系统只允许添加商品分类
    - 1.0.1b 已有商品
        - 1.在该目录中已经有商品的情况下用户选择添加操作
        - 2.系统只允许用户添加新的商品
    - 1.0.1c 已有商品分类
        - 1.在该目录中已经有商品分类的情况下用户选择添加操作
        - 2.系统只允许用户添加新的商品分类
    - 3a 信息不完整
        - 1.必选或必填项目信息缺失时用户选择提交
        - 2.系统返回“请填写完整信息”后返回编辑界面
    - 4a 非法输入提示
        - 1.管理人员输入非法数据并提交：
            - 整数部分超过8位
            - 小数部分超过2位
            - 负数
            - 除小数点以外的其它非数字字符
        - 2.系统返回“非法输入”并返回编辑界面
    - 4b 重名
        - 1.用户选择提交
        - 2.检测出重名，系统返回“名称重复”并返回编辑界面（商品名称与型号都相同则视为重名）
    - 1.1.1a 不可删除
        - 1.用户选择对已标记但不可删除的对象进行删除
        - 2.系统返回“该对象不可删除”
 
- **特殊需求**
    - 无


- **ID**
    - 2
- **名称**
    - 库存查看
- **创建者**
    - 温曜铭
- **修改者**
    - 温曜铭
- **创建日期**
    - 17/09/09
- **修改日期**
    - 17/11/01
- **参与者**
    - 库存管理人员，查看一段时间内的库存数量与财务的变化
- **触发条件**
    - 无
- **前置条件**
    - 仓库管理人员身份被识别并授权
- **后置条件**
    - 无
- **优先级**
    - 中
- **正常流程**
    - 1.用户选择查看库存功能
    - 2.系统返回表格，包括起始时间和结束时间
    - 3.用户设定要查看的时间段（用户可以选择的时间段由系统提供一个可选择的范围而不是任意时间，该系统建立的最初时间点作为时间的下限，时间上限为操作的当天日期的最后一个整数小时）后选择提交
    - 4.系统返回该时间段内出/入库数量、进/出货销售金额与库存数量的合计
    - 5.用户选择返回上一级界面
    - 6.系统返回上一级界面

- **扩展流程**
    - 3a 时间设定错误
        - 1.用户选择结束时间早于开始时间
        - 2.系统提示“结束时间早于开始时间”
- **特殊需求**
    - 无

- **ID**
    - 3
- **名称**
    - 库存盘点
- **创建者**
    - 温曜铭
- **修改者**
    - 温曜铭
- **创建日期**
    - 17/09/09
- **修改日期**
    - 17/11/01
- **参与者**
    - 仓库管理人员，目的是盘点库存，方便管理人员了解信息以便采取一些后续的措施对库存进行调整
- **触发条件**
    - 无
- **前置条件**
    - 仓库管理人员身份被识别并授权
- **后置条件**
    - 无
- **优先级**
    - 中
- **正常流程**
    - 1.0 浏览全部信息
        - 1.用户选择浏览全部信息
        - 2.系统获取当前操作时间并返回当天的各种商品的名称、型号、库存数量、进货均价、销售均价、出厂日期（默认按名称排序）
        - 3.用户可以选择按照下列字段排序：
            - a)名称
            - b)库存数量
            - c)销售均价
            - d)进货均价
            - e)出厂日期
        - 4.系统返回排序后数据
        - 5.用户选择返回上一级界面
        - 6.系统返回上一级界面
    - 1.1 关键字查找
        - 1.用户通过编号、名称、型号查找或按分类查找的方式进行查询某商品的库存快照
        - 2.系统返回该商品的库存快照
        - 3.用户选择返回上一级界面
        - 4.系统返回上一级界面
    - 1.2 导出excel
        - 1.用户选择“将库存快照导出到Excel”
        - 2.系统返回Excel表格
- **扩展流程**
    无

- **特殊需求**
    - 无

- **ID**
    - 4
- **名称**
    - 库存管理报单
- **创建者**
    - 吴新宇
- **修改者**
    - 温曜铭
- **创建日期**
    - 17/09/10
- **修改日期**
    - 17/11/03
- **参与者**
    - 库存管理人员创建各种表单交付审核
- **触发条件**
    - 无
- **前置条件**
    - 库存管理人员身份被识别并授权
- **后置条件**
    - 无
- **优先级**
    - 中
- **正常流程**
    - 1.库存管理人员选择所要创建的表单类型
        - 表单类型包括：
            - a) 库存赠送单
            - b) 库存报损单
            - c) 库存报溢单
    - 2.系统返回表单框架，显示可填写部分
        - 可填部分：
            - a)商品(必选)
            - b)商品数量(必填)
            - c)赠送对象(必选，从已有用户中选择，仅限库存赠送单包含此字段)
    - 3.0 提交单据
        - 1.用户填写单据并选择提交
        - 2.更新系统并展示单据，系统交付审核，审核通过后系统更改库存信息
    - 3.1 保存单据
        - 1.用户填写单据并选择保存
        - 2.系统保存单据为草稿
    
- **扩展流程**
    - 1.0a
        - 1.用户新建单据时选择打开草稿
        - 2.系统打开之前用户保存的草稿单据
    - 3a 非法输入提示
        - 1.管理人员输入非法数据并提交：
            - 整数部分超过8位
            - 小数部分超过2位
            - 负数
            - 除小数点以外的其它非数字字符
        - 2.系统返回“非法输入”并返回编辑界面
    - 3c 中途退出
        - 1.用户选择提交选项之前退出系统     
        - 2.系统返回“保存/不保存/取消”
        - 3.1 保存
            - 1.用户选择保存
            - 2.系统保存草稿并返回上一级界面
        - 3.2 不保存
            - 1.用户选择不保存
            - 2.系统不作任何修改并返回上一级界面
        - 3.3 取消
            - 1.用户选择取消
            - 2.系统不退出并返回编辑界面
    - 3d 信息不完整
        - 1.必选或必填项目信息缺失时用户选择提交
        - 2.系统不允许提交
- **特殊需求**
    - 无

- **ID**
    - 5
- **名称**
    - 库存报警单
- **创建者**
    - 吴新宇
- **修改者**
    - 温曜铭
- **创建日期**
    - 17/09/10
- **修改日期**
    - 17/11/01
- **参与者**
    - 库存管理人员收到某商品低于警戒线的提示，防止库存不够的情况
- **触发条件**
    - 无
- **前置条件**
    - 库存管理人员身份被识别并授权，某商品警戒数量已经设置
- **后置条件**
    - 无
- **优先级**
    - 高
- **正常流程**
    - 1.用户设置警戒值阈值
    - 2.系统更新警戒值
    - 3.销售／进货退货／报损／赠送单据通过审批后某种商品的库存数量低于警戒数量
    - 4.系统向库存管理人员发送消息，消息内容为一个表格，包括低于警报值的商品表单和每件商品缺损的数额和该商品现在的库存数量
- **扩展流程**
    - 参见用例21消息查看
- **特殊需求**
    - 消息阅读只需一步。

---

- **ID**
    - 6
- **名称**
    - 客户管理
- **创建者**
    - 徐一舟
- **修改者**
    - 徐一舟
- **创建日期**
    - 17/09/13
- **修改日期**
    - 17/11/04
- **参与者**
    - 进货销售人员，目的是为了管理客户
- **触发条件**
    - 无
- **前置条件**
    - 销售人员被识别并授权
- **后置条件**
    - 更新客户信息
- **优先级**
    - 高
- **正常流程**
    - 1.0 进货销售人员选择增加功能
        - 1.销售人员输入要增加的客户信息（包括分类，级别，姓名，电话，地址，邮编，电子邮箱，默认业务员，应付额度）并选择提交
        - 2.系统更新信息并显示更新后的信息
    - 1.1 进货销售人员选择查询功能
        - 1.销售进货人员输入客户编号、姓名进行模糊查找
        - 2.系统显示客户信息，包括：
            - 编号
            - 分类（进货商、销售商）
            - 级别（五级，一级普通用户，五级VIP客户）
            - 姓名
            - 电话
            - 地址
            - 邮编
            - 电子邮箱
            - 应付额度
            - 应收
            - 应付
            - 默认业务员
    - 1.2 进货销售人员选择修改功能
        - 1.进货销售人员选择要修改的信息
        - 2.系统返回修改表格，其中可修改的信息包括分类，级别，姓名，电话，地址，邮编，电子邮箱，默认业务员，应付额度
        - 3.进货销售人员输入修改内容并选择提交
        - 4.系统更新信息并显示更新后的信息
    - 1.3 进货销售人员选择删除功能
        - 1.进货销售人员选中要删除的对象执行删除
        - 2.系统返回“是否确认删除”
        - 3.进货销售人员选择确认删除选项
        - 4.系统更新信息并显示更新后的信息

- **扩展流程**
    - 1.0.1/1.2.3a 系统检查输入
        - 1.进货销售人员输入非法数据，包括：
            - 电话中包含非数字
            - 应付额度中包含非数字
            - 邮编中包含非数字
            - 数字的整数部分超过8位
            - 数字的小数部分超过2位
            - 数字为负数
        - 2.系统提示“非法输入”返回编辑界面
    - 1.2.3b 客户与数据库中已有客户的姓名和电话重复
        - 1.用户提交后检测出重名
        - 2.系统返回“存在重名”并返回编辑界面
    - 1.2.2a 修改应付额度时检查用户权限，仅最高权限可修改
        - 1.用户非最高权限修改应付额度
        - 2.系统提示无权限修改该信息
    - 1.3a 不可删除
        - 1.进货销售人员选择删除不可删除的客户
        - 2.系统返回“不可删除”
- **特殊需求**
    - 无

---

- **ID**
    - 7
- **名称**
    - 制定进货单
- **创建者**
    - 徐一舟
- **修改者**
    - 徐一舟
- **创建日期**
    - 17/09/13
- **修改日期**
    - 17/11/04
- **参与者**
    - 进货销售人员，目的是为了反应进货情况
- **触发条件**
    - 发生了进货行为
- **前置条件**
    - 进货销售人员被识别和授权
- **后置条件**
    - 系统保存进货单并将其设置为已提交状态
- **优先级**
    - 高
- **正常流程** 
    - 1.进货销售人员选择填写进货单
    - 2.系统显示单据模板，包括：
        - 单据编号（JHD-yyyyMMdd-xxxxx）
        - 供应商（从客户列表选择，仅显示进货商）
        - 仓库（手动输入）
        - 操作员（当前登录用户，不可修改）
        - 入库商品列表，包括：
            - 商品编号（选择商品后自动显示）
            - 名称（从商品选择界面进行选择）
            - 型号（选择商品后自动显示）
            - 数量（手动输入）
            - 单价（默认为商品信息中的进价）
            - 金额（自动计算）
            - 备注（手动输入）
        - 备注（手动输入）
        - 总额合计（自动计算）
    - 3.进货销售人员输入单据的各项可修改内容并选择确认提交选项
    - 4.系统保存单据并将单据设置为待审核状态，显示更新后的单据并返回上级界面。单据通过审批后，更改库存数据和客户的应收应付数据

- **扩展流程**
    - 1a 用户选择填写进货单草稿
        - 1.系统内有进货单草稿，系统返回进货单草稿
        - 2.系统内无进货单草稿，系统返回新建单据模板
    - 3a 系统检测输入
        - 1.进货销售人员输入非法数据并提交：
            - 整数部分超过8位
            - 小数部分超过2位
            - 负数
            - 除小数点以外的其它非数字字符
        - 2.系统提示“非法输入”返回编辑界面
    - 3b 保存草稿
        - 1.用户选择保存草稿
        - 2.系统将单据保存为草稿，显示更新后信息并返回上一级界面
    - 3.2a 进货销售人员未保存便选择退出
        - 1.系统提示是否保存当前单据及是否确认退出
        - 2.1 保存
            - 2.1.1 用户选择保存并退出
            - 2.1.2 系统保存当前单据草稿并返回上级界面
        - 2.2 不保存
            - 2.2.1 用户选择不保存并退出
            - 2.2.2 系统不保存当前单据并返回上级界面
        - 2.3 取消
            - 2.3.1 用户选择取消退出
            - 2.3.2 系统返回编辑界面

- **特殊需求**
    - 无

---

- **ID**
    - 8
- **名称**
    - 制定销售单
- **创建者**
    - 徐一舟
- **修改者**
    - 徐一舟
- **创建日期**
    - 17/09/13
- **修改日期**
    - 17/11/04
- **参与者**
    - 进货销售人员，目的是为了反应销售情况
- **触发条件**
    - 发生了销售行为
- **前置条件**
    - 进货销售人员被识别和授权
- **后置条件**
    - 系统保存销售单并将其设置为已提交状态
- **优先级**
    - 高
- **正常流程**
    - 1 进货销售人员选择填写销售单
    - 2 系统显示单据模板，包括：
        - 单据编号（XSD-yyyyMMdd-xxxxx）
        - 客户（从客户列表选择，仅显示销售商）
        - 业务员(初始为默认业务员)
        - 操作员（当前登录用户，不可修改）
        - 仓库（手动输入）
        - 出货商品清单，包括：
            - 编号（选择商品后自动显示）
            - 名称（从商品选择界面选择）
            - 型号（选择商品后自动显示）
            - 数量（手工输入）
            - 单价（默认为商品信息里的销售价，可修改）
            - 金额（自动生成）
            - 商品备注（手动填写）
        - 折让前总额（自动计算）
        - 折让（自动计算）
        - 使用代金券金额（手动填写）
        - 折让后总额（自动计算）
        - 备注（手动填写）
    - 3.进货销售人员输入单据的各项可修改内容并选择确认提交选项
    - 4.系统保存单据并将单据设置为待审核状态，显示更新后的单据并返回上级界面。单据通过审批后，更改库存数据和客户的应收应付数据

- **扩展流程**
    - 1a 用户选择填写销售单草稿
        - 1.系统内有销售单草稿，系统返回销售单草稿
        - 2.系统内无销售单草稿，系统返回新建单据模板
    - 3a 系统检测输入
        - 1.进货销售人员输入非法数据并提交：
            - 整数部分超过8位
            - 小数部分超过2位
            - 负数
            - 除小数点以外的其它非数字字符
        - 2.系统提示“非法输入”返回编辑界面
    - 3b 保存草稿
        - 1.用户选择保存草稿
        - 2.系统将单据保存为草稿，显示更新后信息并返回上一级界面
    - 3.2a 进货销售人员未保存便选择退出
        - 1.系统提示是否保存当前单据及是否确认退出
        - 2.1 保存
            - 2.1.1 用户选择保存并退出
            - 2.1.2 系统保存当前单据草稿并返回上级界面
        - 2.2 不保存
            - 2.2.1 用户选择不保存并退出
            - 2.2.2 系统不保存当前单据并返回上级界面
        - 2.3 取消
            - 2.3.1 用户选择取消退出
            - 2.3.2 系统返回编辑界面

- **特殊需求**
    - 无

---

- **ID**
    - 9
- **名称**
    - 制定进货退货单
- **创建者**
    - 徐一舟
- **修改者**
    - 徐一舟
- **创建日期**
    - 17/09/13
- **修改日期**
    - 17/11/04
- **参与者**
    - 进货销售人员，目的是为了反应进货退货情况
- **触发条件**
    - 发生了进货退货行为
- **前置条件**
    - 进货销售人员被识别和授权
- **后置条件**
    - 系统保存进货退货单并将其设置为已提交状态
- **优先级**
    - 高
- **正常流程**
    - 1 进货销售人员选择填写进货单
    - 2 系统显示单据模板，包括：
        - 单据编号（JHTHD-yyyyMMdd-xxxxx）
        - 供应商（从客户列表选择，仅显示进货商）
        - 仓库（手动输入）
        - 操作员（当前登录用户，不可修改）
        - 入库商品列表，包括：
            - 商品编号（选择商品后自动显示）
            - 名称（从商品选择界面进行选择）
            - 型号（选择商品后自动显示）
            - 数量（手动输入，不可超过原进货单）
            - 单价（默认为商品信息中的进价）
            - 金额（自动计算）
            - 备注（手动输入）
        - 备注（手动输入）
        - 总额合计（自动计算）
    - 3.进货销售人员输入单据的各项可修改内容并选择确认提交选项
    - 4.系统保存单据并将单据设置为待审核状态，显示更新后的单据并返回上级界面。单据通过审批后，更改库存数据和客户的应收应付数据


- **扩展流程**
    - 1a 用户选择填写进货退货单草稿
        - 1.系统内有进货退货单草稿，系统返回进货退货单草稿
        - 2.系统内无进货退货单草稿，系统返回新建单据模板
    - 3a 系统检测输入
        - 1.进货销售人员输入非法数据并提交：
            - 整数部分超过8位
            - 小数部分超过2位
            - 负数
            - 除小数点以外的其它非数字字符
        - 2.系统提示“非法输入”返回编辑界面
    - 3b 保存草稿
        - 1.用户选择保存草稿
        - 2.系统将单据保存为草稿，显示更新后信息并返回上一级界面
    - 3.2a 进货销售人员未保存便选择退出
        - 1.系统提示是否保存当前单据及是否确认退出
        - 2.1 保存
            - 2.1.1 用户选择保存并退出
            - 2.1.2 系统保存当前单据草稿并返回上级界面
        - 2.2 不保存
            - 2.2.1 用户选择不保存并退出
            - 2.2.2 系统不保存当前单据并返回上级界面
        - 2.3 取消
            - 2.3.1 用户选择取消退出
            - 2.3.2 系统返回编辑界面

- **特殊需求**
    - 无

---