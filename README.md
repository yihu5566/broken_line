# broken_line 折线图
主要实现简单折线功能，

1.纵坐标是钱

2横坐标是日期

看自己需求是否合适用这个折线。

下面是只用步骤：

```java
mListPoint = new ArrayList<>();
//设置y轴数据
		float[] y = new float[] {1f, 2.5f, 5f,6f, 7.5f, 10f };
		for (int i = 0; i < y.length; i++) {
			HashMap<String, Float> hashMap = new HashMap<String, Float>();
			hashMap.put("key_y", (y[i]));// i*20 +100
			mListPoint.add(hashMap);
		}
		
//设置x轴数据
		findViewById.setXnumber(new String[] { "12-1", "12-2", "12-3", "12-4","12-5" });
		//y轴显示几个值
		findViewById.setYnumber(4);
		//设置纵轴最大值
		findViewById.setMaSxy(10.0f);
```

非常简单，可以自己根据业务来丰富他。