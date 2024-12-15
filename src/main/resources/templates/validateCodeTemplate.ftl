<!DOCTYPE html>

<html>
<head>
  <style>
    .title {
      width: 96px;
      height: 33px;
      font-size: 24px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(103, 105, 110, 1);
      line-height: 33px;
    }

    .hi {
      width: 362px;
      height: 22px;
      font-size: 16px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(103, 105, 110, 1);
      line-height: 22px;
      margin-top: 30px;
    }

    .mainContent {
      width: 440px;
      height: 70px;
      background: rgba(245, 246, 247, 1);
      margin-top: 10px;
      padding: 20px;
    }

    .account {
      width: 250px;
      height: 22px;
      font-size: 16px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(103, 105, 110, 1);
      line-height: 22px;
    }

    .password {
      width: 200px;
      height: 22px;
      font-size: 16px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(103, 105, 110, 1);
      line-height: 22px;
      margin-top: 20px;
    }

    .changepwd {
      width: 250px;
      height: 22px;
      font-size: 16px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(103, 105, 110, 1);
      line-height: 22px;
      padding: 10px 10px 10px 0px;
    }

    .joinArea {
      width: 144px;
      height: 48px;
      background: rgba(39, 143, 224, 1);
      border-radius: 8px;
    }

    .join {
      width: 64px;
      height: 22px;
      font-size: 16px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(255, 255, 255, 1);
      line-height: 22px;
    }

    .bottomLine {
      width: 578px;
      height: 1px;
      background: rgba(228, 231, 233, 1);
    }

    .bottom {
      width: 212px;
      height: 17px;
      font-size: 12px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(140, 144, 149, 1);
      line-height: 17px;
      margin-left: 40px;
      margin-top: 30px;
    }
  </style>
</head>
<body>
<div style="width:578px;height:547px;margin:20px auto;">
  <div style="width:578px;height:80px;background:rgba(248,250,251,1);">
    <div style="float:right;position: relative;width: 82%;padding-top: 10px;height: 80px;">
      <div
          style="font-size:18px;font-family:Avenir-Heavy,Avenir;font-weight:800;color:rgba(37,39,43,1);width:80px;height:25px;line-height:25px;">
        UL Hotel Group co., ltd
      </div>
      <div
          style="width:72px;height:25px;font-size:18px;font-family:PingFangSC-Medium,PingFang SC;font-weight:500;color:rgba(37,39,43,1);line-height:25px;">
        International Booking Platform
      </div>
    </div>
  </div>
  <div style="margin-left:40px;margin-top:30px">
    <div class="title" style="width:96px;
            height:33px;
            font-size:24px;
            font-family:PingFangSC-Medium,PingFang SC;
            font-weight:500;
            color:rgba(103,105,110,1);
            line-height:33px;">Verification
    </div>
    <div class="hi" style="width:362px;
            height:22px;
            font-size:16px;
            font-family:PingFangSC-Regular,PingFang SC;
            font-weight:400;
            color:rgba(103,105,110,1);
            line-height:22px;
            margin-top: 30px;">Your verification code follows：
    </div>
    <div class="mainContent" style=" width:440px;
            height:25px;
            background:rgba(245,246,247,1);
            margin-top:10px;
            padding:20px;">
      <div class="account" style="width:400px;
            height:22px;
            font-size:16px;
            font-family:PingFangSC-Regular,PingFang SC;
            font-weight:400;
            color:rgba(103,105,110,1);
            line-height:22px;">
        <span style="width:100%">Code：</span>
        <span style="color:#278FE0">${code}</span>
      </div>
    </div>
    <div style="height:20px"></div>

  </div>
  <div class="bottomLine" style="width:578px;
            height:1px;
            background:rgba(228,231,233,1);margin-top:20px"></div>

</div>
</body>
</html>