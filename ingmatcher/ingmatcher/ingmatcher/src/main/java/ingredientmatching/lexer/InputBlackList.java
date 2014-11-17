package ch.ingredientmatching.lexer;

import java.util.Arrays;
import java.util.List;

/**
 * isusedbytheNGramAnalyzerwhichimplementsaStopFilteronthesewords
 * 
 * @authornicu
 * 
 */
public class InputBlackList {
	protected static List<String> blackList = Arrays.asList(

	"geschnetzeltes", "geschnetzelt", "geschnetzelter", "gemahlen",
			"gemahlener", "gemahlenes", "gemahlene", "geschnitten",
			"geschnittene", "geschnittener", "geschnittenes", "schneiden",
			"würfel", "gewürfelt", "gewürfeltes", "gewürfelter", "gewürfelte",
			"gehackt", "gehackte", "gehackter", "gehacktes", "gerieben",
			"geriebener", "geriebenes", "geriebene", "zerteilt", "zerteilte",
			"zerteilter", "zerteiltes", "zerteilten", "abgerieben",
			"abgeriebener", "abgeriebenes", "abgeriebene", "halbiert",
			"halbierte", "halbierter", "halbiertes",

			"entsteint", "entsteinte", "entsteinter", "entsteintes",
			"unbehandelt", "unbehandelter", "unbehandelte", "unbehandelten",
			"lauwarm", "lauwarme", "lauwarmes", "lauwarmer", "alter", "alte",
			"altes", "alten", "zerlassen", "zerlassene", "zerlassener",
			"zerlassenes", "frisch", "frische", "frischer", "frischen", "jung",
			"junge", "junger", "junges", "mittelscharf", "mittel-scharf",
			"kalt", "kalter", "kalte", "kaltes", "dünnflüssig",
			"dünnflüssiger", "dünnflüssiges", "bestäuben", "geschält",
			"geschälte", "geschälter", "geschältes", "gepflückt",
			"festkochent", "festkochende", "festkochender", "festkochendes",
			"halbbitter", "halbbittere", "halbbitterer", "bitter", "bittere",
			"bitterer", "abgetropft", "abgetropfte", "abgetropfter",
			"abgetropftes", "küchenfertig", "küchenfertiger", "küchenfertiges",
			"küchenfertige", "getrocknete", "getrocknete", "getrocknet",

			"gebraten", "gebratenes", "gebratener", "gebratene", "gekocht",
			"gekochtes", "gekochter", "ungekocht", "ungekochtes",
			"ungekochter", "roh", "rohes", "roher", "geschmort", "geschmorter",
			"geschmortes", "geschmorten", "geschlagen", "geschlagene",
			"geschlagener", "geschlagenes", "verquirlt", "verquirltes",
			"verquirlte", "passiert", "passierte", "passierter",
			"aufgeschnitten", "aufgeschnittene", "aufgeschnittener",
			"aufgeschnittenes", "aufgeschnittenen", "eingeweicht",
			"eingeweichte", "eingeweichter", "einweichen", "bestreichen",
			"eingelegt", "eingelegte", "eingelegter", "eingelegtes",
			"durchwachsen", "durchwachsener", "durchwachsene", "gemischt",
			"gemischte", "gemischter", "gemischtes", "gequetscht", "tk",
			"garnieren", "entkernt", "entkernte", "entkernter", "entkerntes",
			"weich", "weiche", "weicher", "weiches",

			"belag", "mühle", "form", "geschmack", "typ", "dose", "bio",
			"abrieb", "ringe", "abtropfgewicht", "stück", "stücke", "stücken",
			"glas", "durchmesser", "schale",

			"belieben", "oder", "und", "evtl.", "eventuel", "eventuell",
			"wenig", "weniger", "davon", "ca.", "leicht", "durchwachsen",
			"dünn", "dünnes", "dünner", "ganz", "ganze", "ganzer", "aus",
			"fein", "feine", "feiner", "feines", "für", "der", "die", "das",
			"den", "nach", "je", "z.b.", "zb", "z.b", "zb.", "fest", "feste",
			"fester", "heiss", "heisse", "heisser", "zum", "klein", "kleine",
			"kleiner", "gross", "grosse", "grosser", "ganz", "ganze", "ganzer",
			"usw", "usw.", "vom", "von", "halb", "halber", "halbe", "ohne",
			"klar", "klare", "klarer", "auch"

	);

}
