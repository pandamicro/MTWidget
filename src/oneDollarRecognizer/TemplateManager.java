package oneDollarRecognizer;

/**
 * @author <a href="mailto:gery.casiez@lifl.fr">Gery Casiez</a>
 */

import java.util.Vector;

import mygeom.Point2;
import mygeom.Tuple2;

public class TemplateManager
{
	public static final int TEMPLATE_SIZE = 1;
	//private Vector<Template> theTemplates;
	private Vector<Template> templatesRes;
	public TemplateManager( )
	{
		//theTemplates = new Vector<Template>();
		templatesRes = new Vector<Template>();
		
		// triangle
		Tuple2 [] point0 = {new Tuple2(137,139),new Tuple2(135,141),new Tuple2(133,144),new Tuple2(132,146),
				new Tuple2(130,149),new Tuple2(128,151),new Tuple2(126,155),new Tuple2(123,160),
				new Tuple2(120,166),new Tuple2(116,171),new Tuple2(112,177),new Tuple2(107,183),
				new Tuple2(102,188),new Tuple2(100,191),new Tuple2(95,195),new Tuple2(90,199),
				new Tuple2(86,203),new Tuple2(82,206),new Tuple2(80,209),new Tuple2(75,213),
				new Tuple2(73,213),new Tuple2(70,216),new Tuple2(67,219),new Tuple2(64,221),
				new Tuple2(61,223),new Tuple2(60,225),new Tuple2(62,226),new Tuple2(65,225),
				new Tuple2(67,226),new Tuple2(74,226),new Tuple2(77,227),new Tuple2(85,229),
				new Tuple2(91,230),new Tuple2(99,231),new Tuple2(108,232),new Tuple2(116,233),
				new Tuple2(125,233),new Tuple2(134,234),new Tuple2(145,233),new Tuple2(153,232),
				new Tuple2(160,233),new Tuple2(170,234),new Tuple2(177,235),new Tuple2(179,236),
				new Tuple2(186,237),new Tuple2(193,238),new Tuple2(198,239),new Tuple2(200,237),
				new Tuple2(202,239),new Tuple2(204,238),new Tuple2(206,234),new Tuple2(205,230),
				new Tuple2(202,222),new Tuple2(197,216),new Tuple2(192,207),new Tuple2(186,198),
				new Tuple2(179,189),new Tuple2(174,183),new Tuple2(170,178),new Tuple2(164,171),
				new Tuple2(161,168),new Tuple2(154,160),new Tuple2(148,155),new Tuple2(143,150),
				new Tuple2(138,148),new Tuple2(136,148) };
		AddTemplate("triangle", point0);

		// x
		Tuple2 [] point1 = { new Tuple2(87,142),new Tuple2(89,145),new Tuple2(91,148),new Tuple2(93,151),
				new Tuple2(96,155),new Tuple2(98,157),new Tuple2(100,160),new Tuple2(102,162),
				new Tuple2(106,167),new Tuple2(108,169),new Tuple2(110,171),new Tuple2(115,177),
				new Tuple2(119,183),new Tuple2(123,189),new Tuple2(127,193),new Tuple2(129,196),
				new Tuple2(133,200),new Tuple2(137,206),new Tuple2(140,209),new Tuple2(143,212),
				new Tuple2(146,215),new Tuple2(151,220),new Tuple2(153,222),new Tuple2(155,223),
				new Tuple2(157,225),new Tuple2(158,223),new Tuple2(157,218),new Tuple2(155,211),
				new Tuple2(154,208),new Tuple2(152,200),new Tuple2(150,189),new Tuple2(148,179),
				new Tuple2(147,170),new Tuple2(147,158),new Tuple2(147,148),new Tuple2(147,141),
				new Tuple2(147,136),new Tuple2(144,135),new Tuple2(142,137),new Tuple2(140,139),
				new Tuple2(135,145),new Tuple2(131,152),new Tuple2(124,163),new Tuple2(116,177),
				new Tuple2(108,191),new Tuple2(100,206),new Tuple2(94,217),new Tuple2(91,222),
				new Tuple2(89,225),new Tuple2(87,226),new Tuple2(87,224) } ;
		AddTemplate("x", point1);

		// rectangle
		Tuple2 [] point2 = {new Tuple2(78,149),new Tuple2(78,153),new Tuple2(78,157),new Tuple2(78,160),
				new Tuple2(79,162),new Tuple2(79,164),new Tuple2(79,167),new Tuple2(79,169),
				new Tuple2(79,173),new Tuple2(79,178),new Tuple2(79,183),new Tuple2(80,189),
				new Tuple2(80,193),new Tuple2(80,198),new Tuple2(80,202),new Tuple2(81,208),
				new Tuple2(81,210),new Tuple2(81,216),new Tuple2(82,222),new Tuple2(82,224),
				new Tuple2(82,227),new Tuple2(83,229),new Tuple2(83,231),new Tuple2(85,230),
				new Tuple2(88,232),new Tuple2(90,233),new Tuple2(92,232),new Tuple2(94,233),
				new Tuple2(99,232),new Tuple2(102,233),new Tuple2(106,233),new Tuple2(109,234),
				new Tuple2(117,235),new Tuple2(123,236),new Tuple2(126,236),new Tuple2(135,237),
				new Tuple2(142,238),new Tuple2(145,238),new Tuple2(152,238),new Tuple2(154,239),
				new Tuple2(165,238),new Tuple2(174,237),new Tuple2(179,236),new Tuple2(186,235),
				new Tuple2(191,235),new Tuple2(195,233),new Tuple2(197,233),new Tuple2(200,233),
				new Tuple2(201,235),new Tuple2(201,233),new Tuple2(199,231),new Tuple2(198,226),
				new Tuple2(198,220),new Tuple2(196,207),new Tuple2(195,195),new Tuple2(195,181),
				new Tuple2(195,173),new Tuple2(195,163),new Tuple2(194,155),new Tuple2(192,145),
				new Tuple2(192,143),new Tuple2(192,138),new Tuple2(191,135),new Tuple2(191,133),
				new Tuple2(191,130),new Tuple2(190,128),new Tuple2(188,129),new Tuple2(186,129),
				new Tuple2(181,132),new Tuple2(173,131),new Tuple2(162,131),new Tuple2(151,132),
				new Tuple2(149,132),new Tuple2(138,132),new Tuple2(136,132),new Tuple2(122,131),
				new Tuple2(120,131),new Tuple2(109,130),new Tuple2(107,130),new Tuple2(90,132),
				new Tuple2(81,133),new Tuple2(76,133)};
		AddTemplate("rectangle", point2);

		// circle
		Tuple2 [] point3 = {new Tuple2(127,141),new Tuple2(124,140),new Tuple2(120,139),new Tuple2(118,139),
				new Tuple2(116,139),new Tuple2(111,140),new Tuple2(109,141),new Tuple2(104,144),
				new Tuple2(100,147),new Tuple2(96,152),new Tuple2(93,157),new Tuple2(90,163),
				new Tuple2(87,169),new Tuple2(85,175),new Tuple2(83,181),new Tuple2(82,190),
				new Tuple2(82,195),new Tuple2(83,200),new Tuple2(84,205),new Tuple2(88,213),
				new Tuple2(91,216),new Tuple2(96,219),new Tuple2(103,222),new Tuple2(108,224),
				new Tuple2(111,224),new Tuple2(120,224),new Tuple2(133,223),new Tuple2(142,222),
				new Tuple2(152,218),new Tuple2(160,214),new Tuple2(167,210),new Tuple2(173,204),
				new Tuple2(178,198),new Tuple2(179,196),new Tuple2(182,188),new Tuple2(182,177),
				new Tuple2(178,167),new Tuple2(170,150),new Tuple2(163,138),new Tuple2(152,130),
				new Tuple2(143,129),new Tuple2(140,131),new Tuple2(129,136),new Tuple2(126,139)};

		AddTemplate("circle", point3);

		// check
		Tuple2 [] point4 = {new Tuple2(91,185),new Tuple2(93,185),new Tuple2(95,185),new Tuple2(97,185),new Tuple2(100,188),
				new Tuple2(102,189),new Tuple2(104,190),new Tuple2(106,193),new Tuple2(108,195),new Tuple2(110,198),
				new Tuple2(112,201),new Tuple2(114,204),new Tuple2(115,207),new Tuple2(117,210),new Tuple2(118,212),
				new Tuple2(120,214),new Tuple2(121,217),new Tuple2(122,219),new Tuple2(123,222),new Tuple2(124,224),
				new Tuple2(126,226),new Tuple2(127,229),new Tuple2(129,231),new Tuple2(130,233),new Tuple2(129,231),
				new Tuple2(129,228),new Tuple2(129,226),new Tuple2(129,224),new Tuple2(129,221),new Tuple2(129,218),
				new Tuple2(129,212),new Tuple2(129,208),new Tuple2(130,198),new Tuple2(132,189),new Tuple2(134,182),
				new Tuple2(137,173),new Tuple2(143,164),new Tuple2(147,157),new Tuple2(151,151),new Tuple2(155,144),
				new Tuple2(161,137),new Tuple2(165,131),new Tuple2(171,122),new Tuple2(174,118),new Tuple2(176,114),
				new Tuple2(177,112),new Tuple2(177,114),new Tuple2(175,116),new Tuple2(173,118) };
		AddTemplate("check", point4);

		// caret
		Tuple2 [] point5 = {new Tuple2(79,245),new Tuple2(79,242),new Tuple2(79,239),new Tuple2(80,237),new Tuple2(80,234),
				new Tuple2(81,232),new Tuple2(82,230),new Tuple2(84,224),new Tuple2(86,220),new Tuple2(86,218),
				new Tuple2(87,216),new Tuple2(88,213),new Tuple2(90,207),new Tuple2(91,202),new Tuple2(92,200),
				new Tuple2(93,194),new Tuple2(94,192),new Tuple2(96,189),new Tuple2(97,186),new Tuple2(100,179),
				new Tuple2(102,173),new Tuple2(105,165),new Tuple2(107,160),new Tuple2(109,158),new Tuple2(112,151),
				new Tuple2(115,144),new Tuple2(117,139),new Tuple2(119,136),new Tuple2(119,134),new Tuple2(120,132),
				new Tuple2(121,129),new Tuple2(122,127),new Tuple2(124,125),new Tuple2(126,124),new Tuple2(129,125),
				new Tuple2(131,127),new Tuple2(132,130),new Tuple2(136,139),new Tuple2(141,154),new Tuple2(145,166),
				new Tuple2(151,182),new Tuple2(156,193),new Tuple2(157,196),new Tuple2(161,209),new Tuple2(162,211),
				new Tuple2(167,223),new Tuple2(169,229),new Tuple2(170,231),new Tuple2(173,237),new Tuple2(176,242),
				new Tuple2(177,244),new Tuple2(179,250),new Tuple2(181,255),new Tuple2(182,257) };
		AddTemplate("caret", point5);

		// question
		Tuple2 [] point6 = {new Tuple2(104,145),new Tuple2(103,142),new Tuple2(103,140),new Tuple2(103,138),new Tuple2(103,135),
				new Tuple2(104,133),new Tuple2(105,131),new Tuple2(106,128),new Tuple2(107,125),new Tuple2(108,123),
				new Tuple2(111,121),new Tuple2(113,118),new Tuple2(115,116),new Tuple2(117,116),new Tuple2(119,116),
				new Tuple2(121,115),new Tuple2(124,116),new Tuple2(126,115),new Tuple2(128,114),new Tuple2(130,115),
				new Tuple2(133,116),new Tuple2(135,117),new Tuple2(140,120),new Tuple2(142,121),new Tuple2(144,123),
				new Tuple2(146,125),new Tuple2(149,127),new Tuple2(150,129),new Tuple2(152,130),new Tuple2(154,132),
				new Tuple2(156,134),new Tuple2(158,137),new Tuple2(159,139),new Tuple2(160,141),new Tuple2(160,143),
				new Tuple2(160,146),new Tuple2(160,149),new Tuple2(159,153),new Tuple2(158,155),new Tuple2(157,157),
				new Tuple2(155,159),new Tuple2(153,161),new Tuple2(151,163),new Tuple2(146,167),new Tuple2(142,170),
				new Tuple2(138,172),new Tuple2(134,173),new Tuple2(132,175),new Tuple2(127,175),new Tuple2(124,175),
				new Tuple2(122,176),new Tuple2(120,178),new Tuple2(119,180),new Tuple2(119,183),new Tuple2(119,185),
				new Tuple2(120,190),new Tuple2(121,194),new Tuple2(122,200),new Tuple2(123,205),new Tuple2(123,211),
				new Tuple2(124,215),new Tuple2(124,223),new Tuple2(124,225)};
		AddTemplate("question", point6);

		// arrow
		Tuple2 [] point7 = {new Tuple2(68,222),new Tuple2(70,220),new Tuple2(73,218),new Tuple2(75,217),new Tuple2(77,215),
				new Tuple2(80,213),new Tuple2(82,212),new Tuple2(84,210),new Tuple2(87,209),new Tuple2(89,208),
				new Tuple2(92,206),new Tuple2(95,204),new Tuple2(101,201),new Tuple2(106,198),new Tuple2(112,194),
				new Tuple2(118,191),new Tuple2(124,187),new Tuple2(127,186),new Tuple2(132,183),new Tuple2(138,181),
				new Tuple2(141,180),new Tuple2(146,178),new Tuple2(154,173),new Tuple2(159,171),new Tuple2(161,170),
				new Tuple2(166,167),new Tuple2(168,167),new Tuple2(171,166),new Tuple2(174,164),new Tuple2(177,162),
				new Tuple2(180,160),new Tuple2(182,158),new Tuple2(183,156),new Tuple2(181,154),new Tuple2(178,153),
				new Tuple2(171,153),new Tuple2(164,153),new Tuple2(160,153),new Tuple2(150,154),new Tuple2(147,155),
				new Tuple2(141,157),new Tuple2(137,158),new Tuple2(135,158),new Tuple2(137,158),new Tuple2(140,157),
				new Tuple2(143,156),new Tuple2(151,154),new Tuple2(160,152),new Tuple2(170,149),new Tuple2(179,147),
				new Tuple2(185,145),new Tuple2(192,144),new Tuple2(196,144),new Tuple2(198,144),new Tuple2(200,144),
				new Tuple2(201,147),new Tuple2(199,149),new Tuple2(194,157),new Tuple2(191,160),new Tuple2(186,167),
				new Tuple2(180,176),new Tuple2(177,179),new Tuple2(171,187),new Tuple2(169,189),new Tuple2(165,194),
				new Tuple2(164,196)};
		AddTemplate("arrow", point7);

		// left square bracket
		Tuple2 [] point8 = {new Tuple2(140,124),new Tuple2(138,123),new Tuple2(135,122),new Tuple2(133,123),new Tuple2(130,123),
				new Tuple2(128,124),new Tuple2(125,125),new Tuple2(122,124),new Tuple2(120,124),new Tuple2(118,124),
				new Tuple2(116,125),new Tuple2(113,125),new Tuple2(111,125),new Tuple2(108,124),new Tuple2(106,125),
				new Tuple2(104,125),new Tuple2(102,124),new Tuple2(100,123),new Tuple2(98,123),new Tuple2(95,124),
				new Tuple2(93,123),new Tuple2(90,124),new Tuple2(88,124),new Tuple2(85,125),new Tuple2(83,126),
				new Tuple2(81,127),new Tuple2(81,129),new Tuple2(82,131),new Tuple2(82,134),new Tuple2(83,138),
				new Tuple2(84,141),new Tuple2(84,144),new Tuple2(85,148),new Tuple2(85,151),new Tuple2(86,156),
				new Tuple2(86,160),new Tuple2(86,164),new Tuple2(86,168),new Tuple2(87,171),new Tuple2(87,175),
				new Tuple2(87,179),new Tuple2(87,182),new Tuple2(87,186),new Tuple2(88,188),new Tuple2(88,195),
				new Tuple2(88,198),new Tuple2(88,201),new Tuple2(88,207),new Tuple2(89,211),new Tuple2(89,213),
				new Tuple2(89,217),new Tuple2(89,222),new Tuple2(88,225),new Tuple2(88,229),new Tuple2(88,231),
				new Tuple2(88,233),new Tuple2(88,235),new Tuple2(89,237),new Tuple2(89,240),new Tuple2(89,242),
				new Tuple2(91,241),new Tuple2(94,241),new Tuple2(96,240),new Tuple2(98,239),new Tuple2(105,240),
				new Tuple2(109,240),new Tuple2(113,239),new Tuple2(116,240),new Tuple2(121,239),new Tuple2(130,240),
				new Tuple2(136,237),new Tuple2(139,237),new Tuple2(144,238),new Tuple2(151,237),new Tuple2(157,236),
				new Tuple2(159,237)};
		AddTemplate("left square bracket", point8);

		// right square bracket.
		Tuple2 [] point9 = {new Tuple2(112,138),new Tuple2(112,136),new Tuple2(115,136),new Tuple2(118,137),new Tuple2(120,136),
				new Tuple2(123,136),new Tuple2(125,136),new Tuple2(128,136),new Tuple2(131,136),new Tuple2(134,135),
				new Tuple2(137,135),new Tuple2(140,134),new Tuple2(143,133),new Tuple2(145,132),new Tuple2(147,132),
				new Tuple2(149,132),new Tuple2(152,132),new Tuple2(153,134),new Tuple2(154,137),new Tuple2(155,141),
				new Tuple2(156,144),new Tuple2(157,152),new Tuple2(158,161),new Tuple2(160,170),new Tuple2(162,182),
				new Tuple2(164,192),new Tuple2(166,200),new Tuple2(167,209),new Tuple2(168,214),new Tuple2(168,216),
				new Tuple2(169,221),new Tuple2(169,223),new Tuple2(169,228),new Tuple2(169,231),new Tuple2(166,233),
				new Tuple2(164,234),new Tuple2(161,235),new Tuple2(155,236),new Tuple2(147,235),new Tuple2(140,233),
				new Tuple2(131,233),new Tuple2(124,233),new Tuple2(117,235),new Tuple2(114,238),new Tuple2(112,238)};
		AddTemplate( "right square bracket", point9);

		// v
		Tuple2 [] point10 = {new Tuple2(89,164),new Tuple2(90,162),new Tuple2(92,162),new Tuple2(94,164),new Tuple2(95,166),
				new Tuple2(96,169),new Tuple2(97,171),new Tuple2(99,175),new Tuple2(101,178),new Tuple2(103,182),
				new Tuple2(106,189),new Tuple2(108,194),new Tuple2(111,199),new Tuple2(114,204),new Tuple2(117,209),
				new Tuple2(119,214),new Tuple2(122,218),new Tuple2(124,222),new Tuple2(126,225),new Tuple2(128,228),
				new Tuple2(130,229),new Tuple2(133,233),new Tuple2(134,236),new Tuple2(136,239),new Tuple2(138,240),
				new Tuple2(139,242),new Tuple2(140,244),new Tuple2(142,242),new Tuple2(142,240),new Tuple2(142,237),
				new Tuple2(143,235),new Tuple2(143,233),new Tuple2(145,229),new Tuple2(146,226),new Tuple2(148,217),
				new Tuple2(149,208),new Tuple2(149,205),new Tuple2(151,196),new Tuple2(151,193),new Tuple2(153,182),
				new Tuple2(155,172),new Tuple2(157,165),new Tuple2(159,160),new Tuple2(162,155),new Tuple2(164,150),
				new Tuple2(165,148),new Tuple2(166,146)};
		AddTemplate( "v", point10);

		// delete
		Tuple2 [] point11 = {new Tuple2(123,129),new Tuple2(123,131),new Tuple2(124,133),new Tuple2(125,136),new Tuple2(127,140),
				new Tuple2(129,142),new Tuple2(133,148),new Tuple2(137,154),new Tuple2(143,158),new Tuple2(145,161),
				new Tuple2(148,164),new Tuple2(153,170),new Tuple2(158,176),new Tuple2(160,178),new Tuple2(164,183),
				new Tuple2(168,188),new Tuple2(171,191),new Tuple2(175,196),new Tuple2(178,200),new Tuple2(180,202),
				new Tuple2(181,205),new Tuple2(184,208),new Tuple2(186,210),new Tuple2(187,213),new Tuple2(188,215),
				new Tuple2(186,212),new Tuple2(183,211),new Tuple2(177,208),new Tuple2(169,206),new Tuple2(162,205),
				new Tuple2(154,207),new Tuple2(145,209),new Tuple2(137,210),new Tuple2(129,214),new Tuple2(122,217),
				new Tuple2(118,218),new Tuple2(111,221),new Tuple2(109,222),new Tuple2(110,219),new Tuple2(112,217),
				new Tuple2(118,209),new Tuple2(120,207),new Tuple2(128,196),new Tuple2(135,187),new Tuple2(138,183),
				new Tuple2(148,167),new Tuple2(157,153),new Tuple2(163,145),new Tuple2(165,142),new Tuple2(172,133),
				new Tuple2(177,127),new Tuple2(179,127),new Tuple2(180,125)};
		AddTemplate( "delete", point11);

		// left curly brace
		Tuple2 [] point12 = {new Tuple2(150,116),new Tuple2(147,117),new Tuple2(145,116),new Tuple2(142,116),new Tuple2(139,117),
				new Tuple2(136,117),new Tuple2(133,118),new Tuple2(129,121),new Tuple2(126,122),new Tuple2(123,123),
				new Tuple2(120,125),new Tuple2(118,127),new Tuple2(115,128),new Tuple2(113,129),new Tuple2(112,131),
				new Tuple2(113,134),new Tuple2(115,134),new Tuple2(117,135),new Tuple2(120,135),new Tuple2(123,137),
				new Tuple2(126,138),new Tuple2(129,140),new Tuple2(135,143),new Tuple2(137,144),new Tuple2(139,147),
				new Tuple2(141,149),new Tuple2(140,152),new Tuple2(139,155),new Tuple2(134,159),new Tuple2(131,161),
				new Tuple2(124,166),new Tuple2(121,166),new Tuple2(117,166),new Tuple2(114,167),new Tuple2(112,166),
				new Tuple2(114,164),new Tuple2(116,163),new Tuple2(118,163),new Tuple2(120,162),new Tuple2(122,163),
				new Tuple2(125,164),new Tuple2(127,165),new Tuple2(129,166),new Tuple2(130,168),new Tuple2(129,171),
				new Tuple2(127,175),new Tuple2(125,179),new Tuple2(123,184),new Tuple2(121,190),new Tuple2(120,194),
				new Tuple2(119,199),new Tuple2(120,202),new Tuple2(123,207),new Tuple2(127,211),new Tuple2(133,215),
				new Tuple2(142,219),new Tuple2(148,220),new Tuple2(151,221)};
		AddTemplate( "left curly brace", point12);

		// right curly brace
		Tuple2 [] point13 = {new Tuple2(117,132),new Tuple2(115,132),new Tuple2(115,129),new Tuple2(117,129),new Tuple2(119,128),
				new Tuple2(122,127),new Tuple2(125,127),new Tuple2(127,127),new Tuple2(130,127),new Tuple2(133,129),
				new Tuple2(136,129),new Tuple2(138,130),new Tuple2(140,131),new Tuple2(143,134),new Tuple2(144,136),
				new Tuple2(145,139),new Tuple2(145,142),new Tuple2(145,145),new Tuple2(145,147),new Tuple2(145,149),
				new Tuple2(144,152),new Tuple2(142,157),new Tuple2(141,160),new Tuple2(139,163),new Tuple2(137,166),
				new Tuple2(135,167),new Tuple2(133,169),new Tuple2(131,172),new Tuple2(128,173),new Tuple2(126,176),
				new Tuple2(125,178),new Tuple2(125,180),new Tuple2(125,182),new Tuple2(126,184),new Tuple2(128,187),
				new Tuple2(130,187),new Tuple2(132,188),new Tuple2(135,189),new Tuple2(140,189),new Tuple2(145,189),
				new Tuple2(150,187),new Tuple2(155,186),new Tuple2(157,185),new Tuple2(159,184),new Tuple2(156,185),
				new Tuple2(154,185),new Tuple2(149,185),new Tuple2(145,187),new Tuple2(141,188),new Tuple2(136,191),
				new Tuple2(134,191),new Tuple2(131,192),new Tuple2(129,193),new Tuple2(129,195),new Tuple2(129,197),
				new Tuple2(131,200),new Tuple2(133,202),new Tuple2(136,206),new Tuple2(139,211),new Tuple2(142,215),
				new Tuple2(145,220),new Tuple2(147,225),new Tuple2(148,231),new Tuple2(147,239),new Tuple2(144,244),
				new Tuple2(139,248),new Tuple2(134,250),new Tuple2(126,253),new Tuple2(119,253),new Tuple2(115,253)};
		AddTemplate( "right curly brace", point13);

		// star
		Tuple2 [] point14 = {new Tuple2(75,250),new Tuple2(75,247),new Tuple2(77,244),new Tuple2(78,242),new Tuple2(79,239),
				new Tuple2(80,237),new Tuple2(82,234),new Tuple2(82,232),new Tuple2(84,229),new Tuple2(85,225),
				new Tuple2(87,222),new Tuple2(88,219),new Tuple2(89,216),new Tuple2(91,212),new Tuple2(92,208),
				new Tuple2(94,204),new Tuple2(95,201),new Tuple2(96,196),new Tuple2(97,194),new Tuple2(98,191),
				new Tuple2(100,185),new Tuple2(102,178),new Tuple2(104,173),new Tuple2(104,171),new Tuple2(105,164),
				new Tuple2(106,158),new Tuple2(107,156),new Tuple2(107,152),new Tuple2(108,145),new Tuple2(109,141),
				new Tuple2(110,139),new Tuple2(112,133),new Tuple2(113,131),new Tuple2(116,127),new Tuple2(117,125),
				new Tuple2(119,122),new Tuple2(121,121),new Tuple2(123,120),new Tuple2(125,122),new Tuple2(125,125),
				new Tuple2(127,130),new Tuple2(128,133),new Tuple2(131,143),new Tuple2(136,153),new Tuple2(140,163),
				new Tuple2(144,172),new Tuple2(145,175),new Tuple2(151,189),new Tuple2(156,201),new Tuple2(161,213),
				new Tuple2(166,225),new Tuple2(169,233),new Tuple2(171,236),new Tuple2(174,243),new Tuple2(177,247),
				new Tuple2(178,249),new Tuple2(179,251),new Tuple2(180,253),new Tuple2(180,255),new Tuple2(179,257),
				new Tuple2(177,257),new Tuple2(174,255),new Tuple2(169,250),new Tuple2(164,247),new Tuple2(160,245),
				new Tuple2(149,238),new Tuple2(138,230),new Tuple2(127,221),new Tuple2(124,220),new Tuple2(112,212),
				new Tuple2(110,210),new Tuple2(96,201),new Tuple2(84,195),new Tuple2(74,190),new Tuple2(64,182),
				new Tuple2(55,175),new Tuple2(51,172),new Tuple2(49,170),new Tuple2(51,169),new Tuple2(56,169),
				new Tuple2(66,169),new Tuple2(78,168),new Tuple2(92,166),new Tuple2(107,164),new Tuple2(123,161),
				new Tuple2(140,162),new Tuple2(156,162),new Tuple2(171,160),new Tuple2(173,160),new Tuple2(186,160),
				new Tuple2(195,160),new Tuple2(198,161),new Tuple2(203,163),new Tuple2(208,163),new Tuple2(206,164),
				new Tuple2(200,167),new Tuple2(187,172),new Tuple2(174,179),new Tuple2(172,181),new Tuple2(153,192),
				new Tuple2(137,201),new Tuple2(123,211),new Tuple2(112,220),new Tuple2(99,229),new Tuple2(90,237),
				new Tuple2(80,244),new Tuple2(73,250),new Tuple2(69,254),new Tuple2(69,252)};
		AddTemplate( "star", point14);

		// pig tail
		Tuple2 [] point15 = {new Tuple2(81,219),new Tuple2(84,218),new Tuple2(86,220),new Tuple2(88,220),new Tuple2(90,220),
				new Tuple2(92,219),new Tuple2(95,220),new Tuple2(97,219),new Tuple2(99,220),new Tuple2(102,218),
				new Tuple2(105,217),new Tuple2(107,216),new Tuple2(110,216),new Tuple2(113,214),new Tuple2(116,212),
				new Tuple2(118,210),new Tuple2(121,208),new Tuple2(124,205),new Tuple2(126,202),new Tuple2(129,199),
				new Tuple2(132,196),new Tuple2(136,191),new Tuple2(139,187),new Tuple2(142,182),new Tuple2(144,179),
				new Tuple2(146,174),new Tuple2(148,170),new Tuple2(149,168),new Tuple2(151,162),new Tuple2(152,160),
				new Tuple2(152,157),new Tuple2(152,155),new Tuple2(152,151),new Tuple2(152,149),new Tuple2(152,146),
				new Tuple2(149,142),new Tuple2(148,139),new Tuple2(145,137),new Tuple2(141,135),new Tuple2(139,135),
				new Tuple2(134,136),new Tuple2(130,140),new Tuple2(128,142),new Tuple2(126,145),new Tuple2(122,150),
				new Tuple2(119,158),new Tuple2(117,163),new Tuple2(115,170),new Tuple2(114,175),new Tuple2(117,184),
				new Tuple2(120,190),new Tuple2(125,199),new Tuple2(129,203),new Tuple2(133,208),new Tuple2(138,213),
				new Tuple2(145,215),new Tuple2(155,218),new Tuple2(164,219),new Tuple2(166,219),new Tuple2(177,219),
				new Tuple2(182,218),new Tuple2(192,216),new Tuple2(196,213),new Tuple2(199,212),new Tuple2(201,211)};
		AddTemplate( "pigtail", point15);
	}

	public void AddTemplate( String name, Tuple2 [] points)
	{
		/*
		Vector<Tuple2> pts = new Vector<Tuple2>();
		for (int i = 0; i < points.length; i++) {
			pts.add(points[i]);
		}
		theTemplates.add(new Template(name, pts));
		*/
		
		Point2[] temP = new Point2[points.length];
		for(int i = 0; i < points.length; i++){
			temP[i] = points[i].toPoint2();
		}
		
		Point2[] newPoints = OneDollarRecognizer.resample(temP);
		newPoints = OneDollarRecognizer.rotateToZero(newPoints);
		newPoints = OneDollarRecognizer.scaleToSquare(newPoints, TEMPLATE_SIZE);
		newPoints = OneDollarRecognizer.translateToOrigin(newPoints);
		
		Vector<Tuple2> newPts = new Vector<Tuple2>();
		for (int i = 0; i < newPoints.length; i++) {
			newPts.add(newPoints[i]);
		}
		templatesRes.add(new Template(name, newPts));
	}
	
	public Vector<Template> getTemplates() {
		//return theTemplates;
		return templatesRes;
	}
}