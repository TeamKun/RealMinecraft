# This is "Real" Minecraft!

## description

- ワールドに地雷を設置するPluginです。
- 地雷は目で見えません。
- サバイバル・アドベンチャーモードのとき爆発が起きます。
- ボートや馬の上に載っていても爆発します。
- 地雷はオーバーワールド・ネザーで座標が共有されていることを確認しています。

## usage

- 個々の地雷の設置・確認・解除は専用ツールを使用してください（実装予定）

### Command List

| Command | description |
| :------ | :---------- |
| `/real help` | ヘルプ表示 |
| `/real on` | プラグインを有効化 |
| `/real off` | プラグインを無効化 |
| `/real set pos1 x座標 z座標` | ランダム設置の始点を設定 |
| `/real set pos2 x座標 z座標` | ランダム設置の終点を設定 |
| `/real set power` | 爆発の大きさの設定 |
| `/real set probability` | ランダム設置の頻度 |
| `/real autolay` | pos1とpos2で指定した範囲に地雷を自動設置 |
| `/real autolay x1, z1, x2, z2` | 範囲を指定して地雷を自動設置 |
| `/real sweepall` | 設置した地雷を全撤去する |

## TODO List

- 個々の地雷の設置・確認・解除用専用ツールを実装
- ツールを配るためのコマンドを実装？
