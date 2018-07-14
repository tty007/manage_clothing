# manage_clothing
management tool of cloths by Java(コマンドラインで操作)

実行する際は必ずクラスパスを指定してプログラムを起動する。起動するプログラムはloginbase.java。

java -classpath ".;sqlite-jdbc-3.23.1.jar" loginbase

起動したら指定の操作に従ってツールを操作する。
衣装の追加はsqlで直接insertする必要がある。
