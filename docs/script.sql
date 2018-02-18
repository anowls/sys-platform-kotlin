CREATE TABLE sys_account
(
  id              CHAR(32)                      NOT NULL
  COMMENT '用户ID'
    PRIMARY KEY,
  work_id         BIGINT AUTO_INCREMENT
  COMMENT '自增列',
  org_id          CHAR(32)                      NOT NULL
  COMMENT '组织机构ID',
  sys_code        VARCHAR(32)                   NOT NULL
  COMMENT '业务编码，根据规则自定义生成唯一编码',
  login_name      VARCHAR(50)                   NOT NULL
  COMMENT '登录名',
  password_verify CHAR(32)                      NOT NULL
  COMMENT '登录密码',
  user_name       VARCHAR(100)                  NOT NULL
  COMMENT '姓名',
  short_name      VARCHAR(50)                   NULL
  COMMENT '简称',
  theme           VARCHAR(50) DEFAULT 'default' NOT NULL
  COMMENT '主题',
  account_avatar  VARCHAR(100)                  NULL
  COMMENT '头像',
  contact_number  VARCHAR(32)                   NULL
  COMMENT '联系电话',
  contact_mail    VARCHAR(320)                  NULL
  COMMENT '邮箱',
  id_card         VARCHAR(32)                   NOT NULL
  COMMENT '唯一标识',
  sex_code        VARCHAR(32)                   NULL
  COMMENT '性别码(GB T 2261_1 人的性别代码)',
  pinyin          VARCHAR(500)                  NULL
  COMMENT '用户拼音',
  user_type       TINYINT(1) DEFAULT '0'        NOT NULL
  COMMENT '是否管理员:0 普通用户 1 管理员（ps:用于superadmin和运维人员等特殊账号）',
  status          TINYINT(1) DEFAULT '1'        NOT NULL
  COMMENT '状态:1 启用 0 停用',
  creator_id      CHAR(32)                      NULL
  COMMENT '创建人',
  create_time     DATETIME                      NULL
  COMMENT '创建时间',
  modifier_id     CHAR(32)                      NULL
  COMMENT '修改人',
  modify_time     DATETIME                      NULL
  COMMENT '修改时间',
  deleted         TINYINT(1) DEFAULT '1'        NOT NULL
  COMMENT '状态:1 已删除 0 未删除',
  CONSTRAINT unique_user_work_id
  UNIQUE (work_id)
)
  COMMENT '系统管理_用户信息'
  ENGINE = InnoDB;

CREATE TABLE sys_account_org
(
  id          CHAR(32)               NOT NULL
  COMMENT '主键ID'
    PRIMARY KEY,
  work_id     BIGINT AUTO_INCREMENT
  COMMENT '自增列',
  account_id  CHAR(32)               NOT NULL
  COMMENT '账户ID',
  org_id      CHAR(32)               NOT NULL
  COMMENT '组织机构ID',
  org_default TINYINT(1) DEFAULT '0' NOT NULL
  COMMENT '是否默认机构: 0、否 1、是',
  status      TINYINT(1) DEFAULT '1' NOT NULL
  COMMENT '状态: 1、启用 0、停用',
  creator_id  CHAR(32)               NULL
  COMMENT '创建人',
  create_time DATETIME               NULL
  COMMENT '创建时间',
  modifier_id CHAR(32)               NULL
  COMMENT '修改人',
  modify_time DATETIME               NULL
  COMMENT '修改时间',
  deleted     TINYINT(1) DEFAULT '0' NOT NULL
  COMMENT '删除标记：0、未删除 1、已删除',
  CONSTRAINT unique_org_scope_work_id
  UNIQUE (work_id)
)
  COMMENT '系统管理_账户组织机构'
  ENGINE = InnoDB;

CREATE TABLE sys_account_org_authority
(
  id            CHAR(32)               NOT NULL
  COMMENT '主键ID'
    PRIMARY KEY,
  work_id       BIGINT AUTO_INCREMENT
  COMMENT '自增列',
  account_id    CHAR(32)               NOT NULL
  COMMENT '账户ID',
  org_id        CHAR(32)               NOT NULL
  COMMENT '组织机构ID',
  org_authority TINYINT(1) DEFAULT '0' NOT NULL
  COMMENT '是否有管理权限',
  org_default   TINYINT(1) DEFAULT '0' NOT NULL
  COMMENT '是否默认机构',
  creator_id    CHAR(32)               NULL
  COMMENT '创建人',
  create_time   DATETIME               NULL
  COMMENT '创建时间',
  modifier_id   CHAR(32)               NULL
  COMMENT '修改人',
  modify_time   DATETIME               NULL
  COMMENT '修改时间',
  deleted       TINYINT(1) DEFAULT '0' NOT NULL
  COMMENT '删除标记：0、未删除 1、已删除',
  CONSTRAINT unique_org_upper_work_id
  UNIQUE (work_id)
)
  COMMENT '系统管理_账户权限级别'
  ENGINE = InnoDB;

CREATE TABLE sys_account_role
(
  id          CHAR(32)               NOT NULL
  COMMENT '主键ID'
    PRIMARY KEY,
  work_id     BIGINT AUTO_INCREMENT
  COMMENT '自增列',
  account_id  CHAR(32)               NOT NULL
  COMMENT '账户ID',
  role_id     CHAR(32)               NOT NULL
  COMMENT '角色ID',
  creator_id  CHAR(32)               NULL
  COMMENT '创建人',
  create_time DATETIME               NULL
  COMMENT '创建时间',
  modifier_id CHAR(32)               NULL
  COMMENT '修改人',
  modify_time DATETIME               NULL
  COMMENT '修改时间',
  deleted     TINYINT(1) DEFAULT '0' NOT NULL
  COMMENT '删除标记：0、未删除 1、已删除',
  CONSTRAINT unique_user_role_work_id
  UNIQUE (work_id)
)
  COMMENT '系统管理_账户角色'
  ENGINE = InnoDB;

CREATE TABLE sys_app_group
(
  id          CHAR(32)               NOT NULL
  COMMENT '主键ID'
    PRIMARY KEY,
  work_id     BIGINT AUTO_INCREMENT
  COMMENT '自增列',
  ag_code     VARCHAR(10)            NOT NULL
  COMMENT '编码',
  ag_name     VARCHAR(60)            NOT NULL
  COMMENT '名称',
  ag_desc     VARCHAR(150)           NULL
  COMMENT '描述',
  sort_number SMALLINT(6)            NOT NULL
  COMMENT '排序号',
  status      TINYINT(1) DEFAULT '1' NOT NULL
  COMMENT '状态 1 启用 0 停用',
  creator_id  CHAR(32)               NULL
  COMMENT '创建人',
  create_time DATETIME               NULL
  COMMENT '创建时间',
  modifier_id CHAR(32)               NULL
  COMMENT '修改人',
  modify_time DATETIME               NULL
  COMMENT '修改时间',
  deleted     TINYINT(1) DEFAULT '0' NOT NULL
  COMMENT '删除标记：0、未删除 1、已删除',
  CONSTRAINT unique_app_group_work_id
  UNIQUE (work_id)
)
  COMMENT '应用管理_应用组'
  ENGINE = InnoDB;

CREATE TABLE sys_application
(
  id            CHAR(32)               NOT NULL
  COMMENT '应用ID'
    PRIMARY KEY,
  work_id       BIGINT AUTO_INCREMENT
  COMMENT '自增列',
  app_group_id  CHAR(32)               NOT NULL
  COMMENT '应用组ID',
  app_code      VARCHAR(50)            NOT NULL
  COMMENT '编码',
  app_name      VARCHAR(100)           NOT NULL
  COMMENT '名称',
  app_level     TINYINT DEFAULT '1'    NOT NULL
  COMMENT '级别: 1、运维 2、业务',
  app_target    TINYINT(1) DEFAULT '1' NOT NULL
  COMMENT '打开方式: 1、当前页面 2、新标签页 3、弹出窗口',
  app_url       VARCHAR(150)           NULL
  COMMENT '地址',
  app_run_url   VARCHAR(150)           NULL
  COMMENT '运行地址',
  app_icon      VARCHAR(100)           NULL
  COMMENT '图标',
  app_desc      VARCHAR(150)           NULL
  COMMENT '说明',
  app_company   VARCHAR(60)            NULL
  COMMENT '应用厂商',
  company_url   VARCHAR(100)           NULL
  COMMENT '公司网址',
  company_email VARCHAR(50)            NULL
  COMMENT '邮件地址',
  company_phone VARCHAR(50)            NULL
  COMMENT '服务电话',
  linkman       VARCHAR(60)            NULL
  COMMENT '联系人',
  contact_phone VARCHAR(50)            NULL
  COMMENT '联系人电话',
  contact_email VARCHAR(50)            NULL
  COMMENT '联系人邮箱',
  sort_number   SMALLINT(6)            NOT NULL
  COMMENT '排序号',
  status        TINYINT(1) DEFAULT '1' NOT NULL
  COMMENT '状态: 1、启用 0、停用',
  creator_id    CHAR(32)               NULL
  COMMENT '创建人',
  create_time   DATETIME               NULL
  COMMENT '创建时间',
  modifier_id   CHAR(32)               NULL
  COMMENT '修改人',
  modify_time   DATETIME               NULL
  COMMENT '修改时间',
  deleted       TINYINT(1) DEFAULT '0' NOT NULL
  COMMENT '删除标记：0、未删除 1、已删除',
  CONSTRAINT unique_application_work_id
  UNIQUE (work_id)
)
  COMMENT '应用管理_应用信息'
  ENGINE = InnoDB;

CREATE TABLE sys_configuration
(
  id          CHAR(32)                NOT NULL
  COMMENT '主键ID'
    PRIMARY KEY,
  work_id     BIGINT AUTO_INCREMENT
  COMMENT '自增列',
  data_type   VARCHAR(20)             NULL
  COMMENT '数据类型',
  cfg_key     VARCHAR(50)             NULL
  COMMENT '键',
  cfg_value   VARCHAR(500)            NULL
  COMMENT '值',
  cfg_desc    VARCHAR(150)            NULL
  COMMENT '描述',
  org_id      CHAR(32)                NULL
  COMMENT '范围机构ID',
  status      SMALLINT(6) DEFAULT '1' NULL
  COMMENT '状态 0、停用 1、启用',
  creator_id  CHAR(32)                NULL
  COMMENT '创建人',
  create_time DATETIME                NULL
  COMMENT '创建时间',
  modifier_id CHAR(32)                NULL
  COMMENT '修改人',
  modify_time DATETIME                NULL
  COMMENT '修改时间',
  deleted     SMALLINT(6) DEFAULT '0' NOT NULL
  COMMENT '删除标记：0、未删除 1、已删除',
  CONSTRAINT unique_configuration_work_id
  UNIQUE (work_id)
)
  COMMENT '配置管理_系统参数'
  ENGINE = InnoDB;

CREATE TABLE sys_enum
(
  id          CHAR(32)               NOT NULL
  COMMENT '主键ID'
    PRIMARY KEY,
  work_id     BIGINT AUTO_INCREMENT
  COMMENT '自增列',
  se_ucode    VARCHAR(50)            NULL
  COMMENT '代码',
  se_code     VARCHAR(50)            NOT NULL
  COMMENT '编码',
  se_name     VARCHAR(60)            NOT NULL
  COMMENT '名称',
  se_desc     VARCHAR(150)           NULL
  COMMENT '描述',
  se_level    TINYINT(1) DEFAULT '1' NOT NULL
  COMMENT '类别: 1:标准代码集, 2:扩展代码集, 3:标准数据集, 4:扩展数据集',
  status      TINYINT(1) DEFAULT '1' NOT NULL
  COMMENT '状态: 1 启用 0 停用',
  creator_id  CHAR(32)               NULL
  COMMENT '创建人',
  create_time DATETIME               NULL
  COMMENT '创建时间',
  modifier_id CHAR(32)               NULL
  COMMENT '修改人',
  modify_time DATETIME               NULL
  COMMENT '修改时间',
  deleted     TINYINT(1) DEFAULT '0' NOT NULL
  COMMENT '删除标记：0、未删除 1、已删除',
  CONSTRAINT unique_enum_work_id
  UNIQUE (work_id)
)
  COMMENT '配置管理_数据字典分类'
  ENGINE = InnoDB;

CREATE TABLE sys_enum_item
(
  id              CHAR(32)                NOT NULL
  COMMENT '主键ID'
    PRIMARY KEY,
  work_id         BIGINT AUTO_INCREMENT
  COMMENT '自增列',
  se_id           CHAR(32)                NOT NULL
  COMMENT '码表项ID',
  sei_parent_id   CHAR(32)                NULL
  COMMENT '上级ID',
  sei_parent_code VARCHAR(50)             NULL
  COMMENT '上级代码',
  sei_code        VARCHAR(50)             NOT NULL
  COMMENT '代码',
  sei_name        VARCHAR(60)             NOT NULL
  COMMENT '名称',
  sei_desc        VARCHAR(150)            NULL
  COMMENT '描述',
  pinyin          VARCHAR(100)            NULL
  COMMENT '拼音',
  sei_sort        SMALLINT(6)             NOT NULL
  COMMENT '排序号',
  status          SMALLINT(6) DEFAULT '1' NOT NULL
  COMMENT '状态: 1 启用 0 停用',
  creator_id      CHAR(32)                NULL
  COMMENT '创建人',
  create_time     DATETIME                NULL
  COMMENT '创建时间',
  modifier_id     CHAR(32)                NULL
  COMMENT '修改人',
  modify_time     DATETIME                NULL
  COMMENT '修改时间',
  deleted         SMALLINT(6) DEFAULT '0' NOT NULL
  COMMENT '删除标记：0、未删除 1、已删除',
  CONSTRAINT unique_enum_item_work_id
  UNIQUE (work_id)
)
  COMMENT '配置管理_数据字典明细'
  ENGINE = InnoDB;

CREATE TABLE sys_feature
(
  id              CHAR(32)               NOT NULL
  COMMENT '主键ID'
    PRIMARY KEY,
  work_id         BIGINT AUTO_INCREMENT
  COMMENT '自增列',
  app_id          CHAR(32)               NOT NULL
  COMMENT '应用ID',
  sm_id           CHAR(32)               NOT NULL
  COMMENT '模块ID',
  sf_code         VARCHAR(100)           NOT NULL
  COMMENT '编码',
  sf_name         VARCHAR(100)           NOT NULL
  COMMENT '名称',
  sf_desc         VARCHAR(150)           NULL
  COMMENT '描述',
  sf_type         TINYINT(1) DEFAULT '1' NULL
  COMMENT '类型 1、功能 2、小部件',
  is_menu         TINYINT(1) DEFAULT '0' NOT NULL
  COMMENT '是否菜单项',
  controller_name VARCHAR(150)           NULL
  COMMENT '控制层名称',
  action_name     VARCHAR(150)           NULL
  COMMENT 'Action名称',
  request_url     VARCHAR(150)           NULL
  COMMENT '请求地址',
  status          TINYINT(1) DEFAULT '1' NOT NULL
  COMMENT '状态 1、启用 0、停用',
  creator_id      CHAR(32)               NULL
  COMMENT '创建人',
  create_time     DATETIME               NULL
  COMMENT '创建时间',
  modifier_id     CHAR(32)               NULL
  COMMENT '修改人',
  modify_time     DATETIME               NULL
  COMMENT '修改时间',
  deleted         TINYINT(1) DEFAULT '0' NOT NULL
  COMMENT '删除标记：0、未删除 1、已删除',
  CONSTRAINT unique_feature_work_id
  UNIQUE (work_id)
)
  COMMENT '应用管理_功能项'
  ENGINE = InnoDB;

CREATE TABLE sys_menu
(
  id             CHAR(32)               NOT NULL
  COMMENT '主键ID'
    PRIMARY KEY,
  work_id        BIGINT AUTO_INCREMENT
  COMMENT '自增列',
  app_id         CHAR(32)               NOT NULL
  COMMENT '应用ID',
  sf_id          CHAR(32)               NULL
  COMMENT '功能ID',
  open_type      TINYINT(1)             NULL
  COMMENT '打开方式 0、不处理 1、vue 2、iframe',
  open_url       VARCHAR(500)           NULL
  COMMENT '打开URL',
  menu_code      VARCHAR(100)           NOT NULL
  COMMENT '菜单编码',
  menu_code_path VARCHAR(1000)          NOT NULL
  COMMENT '级联编码',
  sm_parent_id   CHAR(32)               NULL
  COMMENT '上级ID',
  sm_code        VARCHAR(100)           NOT NULL
  COMMENT '编码',
  sm_name        VARCHAR(150)           NOT NULL
  COMMENT '名称',
  sm_caption     VARCHAR(150)           NULL
  COMMENT '标题',
  sm_level       TINYINT                NOT NULL
  COMMENT '层级',
  sm_icon        VARCHAR(150)           NULL
  COMMENT '图标',
  sort_number    SMALLINT(6)            NOT NULL
  COMMENT '排序',
  status         TINYINT(1) DEFAULT '1' NOT NULL
  COMMENT '状态 1、启用 0、停用',
  component      VARCHAR(50)            NULL
  COMMENT '子组件名，运维平台动态子组件专用',
  creator_id     CHAR(32)               NULL
  COMMENT '创建人',
  create_time    DATETIME               NULL
  COMMENT '创建时间',
  modifier_id    CHAR(32)               NULL
  COMMENT '修改人',
  modify_time    DATETIME               NULL
  COMMENT '修改时间',
  deleted        TINYINT(1) DEFAULT '0' NOT NULL
  COMMENT '删除标记：0、未删除 1、已删除',
  CONSTRAINT unique_menu_work_id
  UNIQUE (work_id)
)
  COMMENT '应用管理_菜单'
  ENGINE = InnoDB;

CREATE TABLE sys_module
(
  id          CHAR(32)               NOT NULL
  COMMENT '主键ID'
    PRIMARY KEY,
  work_id     BIGINT AUTO_INCREMENT
  COMMENT '自增列',
  app_id      CHAR(32)               NOT NULL
  COMMENT '应用ID',
  sm_code     VARCHAR(100)           NOT NULL
  COMMENT '编码',
  sm_name     VARCHAR(100)           NOT NULL
  COMMENT '名称',
  sm_desc     VARCHAR(150)           NULL
  COMMENT '描述',
  sort_number SMALLINT(6)            NULL
  COMMENT '排序号',
  status      TINYINT(1) DEFAULT '1' NOT NULL
  COMMENT '状态: 1、启用 0、停用',
  creator_id  CHAR(32)               NULL
  COMMENT '创建人',
  create_time DATETIME               NULL
  COMMENT '创建时间',
  modifier_id CHAR(32)               NULL
  COMMENT '修改人',
  modify_time DATETIME               NULL
  COMMENT '修改时间',
  deleted     TINYINT(1) DEFAULT '0' NOT NULL
  COMMENT '删除标记：0、未删除 1、已删除',
  CONSTRAINT unique_module_work_id
  UNIQUE (work_id)
)
  COMMENT '应用管理_模块'
  ENGINE = InnoDB;

CREATE TABLE sys_organization
(
  id            CHAR(32)               NOT NULL
  COMMENT '主键ID'
    PRIMARY KEY,
  work_id       BIGINT AUTO_INCREMENT
  COMMENT '自增列',
  org_parent_id CHAR(32)               NULL
  COMMENT '上级ID',
  org_code      VARCHAR(10)            NOT NULL
  COMMENT '编码',
  org_code_path VARCHAR(150)           NOT NULL
  COMMENT '级联编码',
  org_name      VARCHAR(60)            NOT NULL
  COMMENT '名称',
  org_name_path VARCHAR(1000)          NOT NULL
  COMMENT '名称路径',
  org_level     SMALLINT(6)            NOT NULL
  COMMENT '层级',
  org_sort      INT                    NOT NULL
  COMMENT '排序号',
  org_logo      VARCHAR(100)           NULL
  COMMENT 'LOGO',
  org_grade     TINYINT(1) DEFAULT '2' NOT NULL
  COMMENT '组织机构级别 ：1、校区 2、中心校 3、区/县 4、市 5、省',
  org_type      TINYINT(1) DEFAULT '0' NOT NULL
  COMMENT '机构类型：0、机构节点 1、分类节点',
  status        TINYINT(1) DEFAULT '1' NOT NULL
  COMMENT '状态: 1 启用 0 停用',
  creator_id    CHAR(32)               NULL
  COMMENT '创建人',
  create_time   DATETIME               NULL
  COMMENT '创建时间',
  modifier_id   CHAR(32)               NULL
  COMMENT '修改人',
  modify_time   DATETIME               NULL
  COMMENT '修改时间',
  deleted       TINYINT(1) DEFAULT '0' NOT NULL
  COMMENT '删除标记：0、未删除 1、已删除',
  CONSTRAINT unique_organization_work_id
  UNIQUE (work_id)
)
  COMMENT '系统管理_组织机构'
  ENGINE = InnoDB;

CREATE TABLE sys_role
(
  id           CHAR(32)               NOT NULL
  COMMENT '主键id'
    PRIMARY KEY,
  work_id      BIGINT AUTO_INCREMENT
  COMMENT '自增列',
  role_type    CHAR(32)               NOT NULL
  COMMENT '角色分类',
  role_code    VARCHAR(50)            NOT NULL
  COMMENT '编码',
  role_name    VARCHAR(60)            NOT NULL
  COMMENT '名称',
  role_desc    VARCHAR(150)           NULL
  COMMENT '描述',
  role_level   TINYINT(1) DEFAULT '2' NOT NULL
  COMMENT '级别: 1、运维 2、业务',
  role_default TINYINT(1) DEFAULT '0' NOT NULL
  COMMENT '是否默认',
  status       TINYINT(1) DEFAULT '1' NULL
  COMMENT '状态 1、启用 0、停用',
  creator_id   CHAR(32)               NULL
  COMMENT '创建人',
  create_time  DATETIME               NULL
  COMMENT '创建时间',
  modifier_id  CHAR(32)               NULL
  COMMENT '修改人',
  modify_time  DATETIME               NULL
  COMMENT '修改时间',
  deleted      TINYINT(1) DEFAULT '0' NOT NULL
  COMMENT '删除标记：0、未删除 1、已删除',
  CONSTRAINT unique_role_work_id
  UNIQUE (work_id)
)
  COMMENT '系统管理_角色'
  ENGINE = InnoDB;

CREATE TABLE sys_role_feature
(
  id          CHAR(32)               NOT NULL
  COMMENT '主键ID'
    PRIMARY KEY,
  work_id     BIGINT AUTO_INCREMENT
  COMMENT '自增列',
  role_id     CHAR(32)               NOT NULL
  COMMENT '角色ID',
  sf_id       CHAR(32)               NOT NULL
  COMMENT '功能ID',
  creator_id  CHAR(32)               NULL
  COMMENT '创建人',
  create_time DATETIME               NULL
  COMMENT '创建时间',
  modifier_id CHAR(32)               NULL
  COMMENT '修改人',
  modify_time DATETIME               NULL
  COMMENT '修改时间',
  deleted     TINYINT(1) DEFAULT '0' NOT NULL
  COMMENT '删除标记：0、未删除 1、已删除',
  CONSTRAINT unique_role_feature_work_id
  UNIQUE (work_id)
)
  COMMENT '系统管理_角色功能项'
  ENGINE = InnoDB;

CREATE TABLE sys_role_org
(
  id          CHAR(32)               NOT NULL
  COMMENT '主键id'
    PRIMARY KEY,
  work_id     BIGINT AUTO_INCREMENT
  COMMENT '自增列',
  org_id      CHAR(32)               NOT NULL
  COMMENT '组织机构ID',
  role_id     CHAR(32)               NOT NULL
  COMMENT '角色ID',
  creator_id  CHAR(32)               NULL
  COMMENT '创建人',
  create_time DATETIME               NULL
  COMMENT '创建时间',
  modifier_id CHAR(32)               NULL
  COMMENT '修改人',
  modify_time DATETIME               NULL
  COMMENT '修改时间',
  deleted     TINYINT(1) DEFAULT '0' NOT NULL
  COMMENT '删除标记：0、未删除 1、已删除',
  CONSTRAINT unique_role_org_work_id
  UNIQUE (work_id)
)
  COMMENT '系统管理_角色组织机构'
  ENGINE = InnoDB;


