import 'package:flutter/material.dart';
import 'package:flutter/material.dart' as material;
import 'package:fl_chart/fl_chart.dart';
import 'dart:math';
import "src/MyChart.dart";

//import 'package:example/utils/color_extensions.dart';

void main() {
  runApp(const MyApp());
}

class UIManager {
  static List<Widget> _items = [];

  static IconButton inclease = new IconButton(
    icon: Icon(Icons.add),
    onPressed: () {
      _items.add(Text('hoge'));
    },
  );

  static List<Widget> getList() {
    if (_items.isEmpty) {
      UIManager.addRow();
    }
    return _items;
  }

  static void addRow() {
    if (_items.isEmpty) {
      _items.add(UIManager._getNewListRow());
    } else {
      Row row = _items.last as Row;
      Container cont = row.children[0] as Container;
      TextField tf = cont.child as TextField;
      TextEditingController tc = tf.controller as TextEditingController;
      String text = tc.text;

      if (text != "") {
        _items.add(UIManager._getNewListRow());
      }
    }
  }

  static Row getControler() {
    return Row(children: [
      IconButton(
        icon: Icon(Icons.add),
        onPressed: () {
          //UIManager.addRow();
        },
      ),
    ]);
  }

  static Row _getNewListRow() {
    return Row(
      children: <Widget>[
        Container(
          child: TextField(
            decoration: InputDecoration(
              border: OutlineInputBorder(),
              labelText: 'ジャンル',
            ),
            controller: new TextEditingController(),
          ),
          //TODO: 指定の仕方ね
          height: 30,
          width: 300,
          margin: EdgeInsets.all(5),
        ),
        Container(
            child: Slider(
          value: _continuousValue.value,
          min: 0,
          max: 100,
        )),
      ],
    );
  }

  static Row getLatestField() => _items.last as Row;
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or simply save your changes to "hot reload" in a Flutter IDE).
        // Notice that the counter didn't reset back to zero; the application
        // is not restarted.
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;
  List<Widget> items = [];

  void affectSliderChange() {
    setState(() {});
  }

  void _incrementCounter() {
    setState(() {
      // This call to setState tells the Flutter framework that something has
      // changed in this State, which causes it to rerun the build method below
      // so that the display can reflect the updated values. If we changed
      // _counter without calling setState(), then the build method would not be
      // called again, and so nothing would appear to happen.
      UIManager.addRow();
    });
  }

  @override
  Widget build(BuildContext context) {
    // This method is rerun every time setState is called, for instance as done
    // by the _incrementCounter method above.
    //
    // The Flutter framework has been optimized to make rerunning build methods
    // fast, so that you can just rebuild anything that needs updating rather
    // than having to individually change instances of widgets.
    return Scaffold(
      appBar: AppBar(
        // Here we take the value from the MyHomePage object that was created by
        // the App.build method, and use it to set our appbar title.
        title: Text(widget.title),
      ),
      body: Center(
          child: Column(children: [
        Container(child: UIManager.getControler()),
        Container(
          child: ListView.builder(
              itemBuilder: (BuildContext context, int index) {
                return UIManager.getList()[index];
              },
              itemCount: UIManager.getList().length),
          height: 250,
          width: 400,
        ),
        Container(
          child: PieChart(
            PieChartData(
              borderData: FlBorderData(show: false),
              sections: showingSections(),
            ),
          ),
          height: 250,
          width: 400,
          //color: Color.fromRGBO(10, 100, 20, 0.5),
        )
      ])),

      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }

  List<PieChartSectionData> showingSections() {
    return List.generate(
      4,
      (i) {
        final isTouched = i == 1;
        final opacity = isTouched ? 1.0 : 0.6;

        const color0 = Color(0xff0293ee);
        const color1 = Color(0xfff8b250);
        const color2 = Color(0xff845bef);
        const color3 = Color(0xff13d38e);

        switch (i) {
          case 0:
            return PieChartSectionData(
              color: color0.withOpacity(opacity),
              value: double.parse("0.022e-04"),
              title: 'hoge',
              radius: 80,
              titleStyle: const TextStyle(
                  fontSize: 18,
                  fontWeight: FontWeight.bold,
                  color: Color(0xff044d7c)),
              titlePositionPercentageOffset: 0.55,
              borderSide: isTouched
                  ? BorderSide(color: color0, width: 6)
                  : BorderSide(color: color0.withOpacity(0)),
            );
          case 1:
            return PieChartSectionData(
              color: color1.withOpacity(opacity),
              value: 0.000001,
              title: '',
              radius: 80,
              titleStyle: const TextStyle(
                  fontSize: 18,
                  fontWeight: FontWeight.bold,
                  color: Color(0xff90672d)),
              titlePositionPercentageOffset: 0.55,
              borderSide: isTouched
                  ? BorderSide(color: color1, width: 6)
                  : BorderSide(color: color2.withOpacity(0)),
            );
          case 2:
            return PieChartSectionData(
              color: color2.withOpacity(opacity),
              value: 0.000001,
              title: '',
              radius: 80,
              titleStyle: const TextStyle(
                  fontSize: 18,
                  fontWeight: FontWeight.bold,
                  color: Color(0xff4c3788)),
              titlePositionPercentageOffset: 0.6,
              borderSide: isTouched
                  ? BorderSide(color: color2, width: 6)
                  : BorderSide(color: color2.withOpacity(0)),
            );
          case 3:
            return PieChartSectionData(
              color: color3.withOpacity(opacity),
              value: 0.000001,
              title: '',
              radius: 70,
              titleStyle: const TextStyle(
                  fontSize: 18,
                  fontWeight: FontWeight.bold,
                  color: Color(0xff0c7f55)),
              titlePositionPercentageOffset: 0.55,
              borderSide: isTouched
                  ? BorderSide(color: color3, width: 6)
                  : BorderSide(color: color2.withOpacity(0)),
            );
          default:
            throw Error();
        }
      },
    );
  }
}
