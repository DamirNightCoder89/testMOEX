package com.damirkan.shareservice.controllers;

import com.damirkan.shareservice.ShareserviceApplication;
import com.damirkan.shareservice.model.Shares;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mediatype.hal.HalConfiguration;
import org.springframework.hateoas.server.reactive.WebFluxLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@RestController
public class MainController {

    String uri_list = "https://iss.moex.com/iss/engines/stock/markets/shares/securities.json?iss.meta=off&iss.version=off&iss.json=extended&iss.only=marketdata&marketdata.columns=SECID";
    String uri = "https://iss.moex.com/iss/engines/stock/markets/shares/securities.json?securities=GAZP&iss.meta=off&iss.version=off&iss.only=marketdata&marketdata.columns=,BOARDID,SECID,LAST";
    WebClient webClient = WebClient.create();
    String jsoni = "[\n" +
            "{\"charsetinfo\": {\"name\": \"utf-8\"}},\n" +
            "{\n" +
            "\"marketdata\": [\n" +
            "{\"SECID\": \"ABRD\", \"LAST\": 192.5},\n" +
            "{\"SECID\": \"ACKO\", \"LAST\": 5.68},\n" +
            "{\"SECID\": \"AFKS\", \"LAST\": 28.749},\n" +
            "{\"SECID\": \"AFLT\", \"LAST\": 66.84},\n" +
            "{\"SECID\": \"AGRO\", \"LAST\": 1153},\n" +
            "{\"SECID\": \"AKRN\", \"LAST\": 7022},\n" +
            "{\"SECID\": \"ALBK\", \"LAST\": 125},\n" +
            "{\"SECID\": \"ALNU\", \"LAST\": null},\n" +
            "{\"SECID\": \"ALRS\", \"LAST\": 139.04},\n" +
            "{\"SECID\": \"AMEZ\", \"LAST\": 17.68},\n" +
            "{\"SECID\": \"APTK\", \"LAST\": 13.46},\n" +
            "{\"SECID\": \"AQUA\", \"LAST\": 480},\n" +
            "{\"SECID\": \"ARSA\", \"LAST\": 7.45},\n" +
            "{\"SECID\": \"ASSB\", \"LAST\": 0.8365},\n" +
            "{\"SECID\": \"AVAN\", \"LAST\": 1135},\n" +
            "{\"SECID\": \"BANE\", \"LAST\": 1425},\n" +
            "{\"SECID\": \"BANEP\", \"LAST\": 1050},\n" +
            "{\"SECID\": \"BELU\", \"LAST\": 3300},\n" +
            "{\"SECID\": \"BISV\", \"LAST\": null},\n" +
            "{\"SECID\": \"BISVP\", \"LAST\": 8.2},\n" +
            "{\"SECID\": \"BLNG\", \"LAST\": 12.87},\n" +
            "{\"SECID\": \"BRZL\", \"LAST\": 1235},\n" +
            "{\"SECID\": \"BSPB\", \"LAST\": 74.33},\n" +
            "{\"SECID\": \"BSPBP\", \"LAST\": 22.25},\n" +
            "{\"SECID\": \"CBOM\", \"LAST\": 7.069},\n" +
            "{\"SECID\": \"CHEP\", \"LAST\": null},\n" +
            "{\"SECID\": \"CHGZ\", \"LAST\": 39.8},\n" +
            "{\"SECID\": \"CHKZ\", \"LAST\": 6100},\n" +
            "{\"SECID\": \"CHMF\", \"LAST\": 1557.6},\n" +
            "{\"SECID\": \"CHMK\", \"LAST\": 5480},\n" +
            "{\"SECID\": \"CNTL\", \"LAST\": 13.96},\n" +
            "{\"SECID\": \"CNTLP\", \"LAST\": 9.54},\n" +
            "{\"SECID\": \"DERZP\", \"LAST\": null},\n" +
            "{\"SECID\": \"DIOD\", \"LAST\": 8.65},\n" +
            "{\"SECID\": \"DSKY\", \"LAST\": 130.52},\n" +
            "{\"SECID\": \"DVEC\", \"LAST\": 0.86},\n" +
            "{\"SECID\": \"DZRD\", \"LAST\": 2230},\n" +
            "{\"SECID\": \"DZRDP\", \"LAST\": 1720},\n" +
            "{\"SECID\": \"EELT\", \"LAST\": 7.44},\n" +
            "{\"SECID\": \"ELTZ\", \"LAST\": 189},\n" +
            "{\"SECID\": \"EM44\", \"LAST\": null},\n" +
            "{\"SECID\": \"ENPG\", \"LAST\": 818.5},\n" +
            "{\"SECID\": \"ENRU\", \"LAST\": 0.8496},\n" +
            "{\"SECID\": \"ETLN\", \"LAST\": 121.98},\n" +
            "{\"SECID\": \"FEES\", \"LAST\": 0.18886},\n" +
            "{\"SECID\": \"FESH\", \"LAST\": 26.4},\n" +
            "{\"SECID\": \"FIVE\", \"LAST\": 2311.5},\n" +
            "{\"SECID\": \"FIXP\", \"LAST\": 605.9},\n" +
            "{\"SECID\": \"FLOT\", \"LAST\": 85.44},\n" +
            "{\"SECID\": \"GAZA\", \"LAST\": 511},\n" +
            "{\"SECID\": \"GAZAP\", \"LAST\": 362.5},\n" +
            "{\"SECID\": \"GAZC\", \"LAST\": null},\n" +
            "{\"SECID\": \"GAZP\", \"LAST\": 339.46},\n" +
            "{\"SECID\": \"GAZS\", \"LAST\": null},\n" +
            "{\"SECID\": \"GAZT\", \"LAST\": null},\n" +
            "{\"SECID\": \"GCHE\", \"LAST\": 3198.5},\n" +
            "{\"SECID\": \"GEMA\", \"LAST\": 943.5},\n" +
            "{\"SECID\": \"GEMC\", \"LAST\": 1069.5},\n" +
            "{\"SECID\": \"GLTR\", \"LAST\": 551.85},\n" +
            "{\"SECID\": \"GMKN\", \"LAST\": 23004},\n" +
            "{\"SECID\": \"GTRK\", \"LAST\": 36.5},\n" +
            "{\"SECID\": \"GTSS\", \"LAST\": null},\n" +
            "{\"SECID\": \"HHRU\", \"LAST\": 3788},\n" +
            "{\"SECID\": \"HIMC\", \"LAST\": null},\n" +
            "{\"SECID\": \"HIMCP\", \"LAST\": 11.4},\n" +
            "{\"SECID\": \"HMSG\", \"LAST\": 337.5},\n" +
            "{\"SECID\": \"HYDR\", \"LAST\": 0.8127},\n" +
            "{\"SECID\": \"IGST\", \"LAST\": 1041},\n" +
            "{\"SECID\": \"IGSTP\", \"LAST\": 1072},\n" +
            "{\"SECID\": \"INGR\", \"LAST\": 1360},\n" +
            "{\"SECID\": \"IRAO\", \"LAST\": 4.605},\n" +
            "{\"SECID\": \"IRGZ\", \"LAST\": 13.58},\n" +
            "{\"SECID\": \"IRKT\", \"LAST\": 24.4},\n" +
            "{\"SECID\": \"ISKJ\", \"LAST\": 86.46},\n" +
            "{\"SECID\": \"JNOS\", \"LAST\": 21.05},\n" +
            "{\"SECID\": \"JNOSP\", \"LAST\": 17.2},\n" +
            "{\"SECID\": \"KAZT\", \"LAST\": 417.2},\n" +
            "{\"SECID\": \"KAZTP\", \"LAST\": 425.4},\n" +
            "{\"SECID\": \"KBSB\", \"LAST\": 139},\n" +
            "{\"SECID\": \"KCHE\", \"LAST\": 0.19},\n" +
            "{\"SECID\": \"KCHEP\", \"LAST\": 0.4},\n" +
            "{\"SECID\": \"KGKC\", \"LAST\": 47.8},\n" +
            "{\"SECID\": \"KGKCP\", \"LAST\": 47.6},\n" +
            "{\"SECID\": \"KLSB\", \"LAST\": 9.85},\n" +
            "{\"SECID\": \"KMAZ\", \"LAST\": 103.3},\n" +
            "{\"SECID\": \"KMEZ\", \"LAST\": 1165},\n" +
            "{\"SECID\": \"KMTZ\", \"LAST\": null},\n" +
            "{\"SECID\": \"KOGK\", \"LAST\": 48600},\n" +
            "{\"SECID\": \"KRKN\", \"LAST\": 12300},\n" +
            "{\"SECID\": \"KRKNP\", \"LAST\": 15040},\n" +
            "{\"SECID\": \"KRKO\", \"LAST\": null},\n" +
            "{\"SECID\": \"KRKOP\", \"LAST\": 11.35},\n" +
            "{\"SECID\": \"KROT\", \"LAST\": 407},\n" +
            "{\"SECID\": \"KROTP\", \"LAST\": 311},\n" +
            "{\"SECID\": \"KRSB\", \"LAST\": 9.07},\n" +
            "{\"SECID\": \"KRSBP\", \"LAST\": 9.4},\n" +
            "{\"SECID\": \"KSGR\", \"LAST\": null},\n" +
            "{\"SECID\": \"KTSB\", \"LAST\": 0.64},\n" +
            "{\"SECID\": \"KTSBP\", \"LAST\": 0.3545},\n" +
            "{\"SECID\": \"KUBE\", \"LAST\": 69.5},\n" +
            "{\"SECID\": \"KUNF\", \"LAST\": null},\n" +
            "{\"SECID\": \"KUZB\", \"LAST\": 0.02293},\n" +
            "{\"SECID\": \"KZMS\", \"LAST\": null},\n" +
            "{\"SECID\": \"KZOS\", \"LAST\": 111.5},\n" +
            "{\"SECID\": \"KZOSP\", \"LAST\": 31.98},\n" +
            "{\"SECID\": \"LIFE\", \"LAST\": 6.065},\n" +
            "{\"SECID\": \"LKOH\", \"LAST\": 6702},\n" +
            "{\"SECID\": \"LNTA\", \"LAST\": 221.7},\n" +
            "{\"SECID\": \"LNZL\", \"LAST\": 11640},\n" +
            "{\"SECID\": \"LNZLP\", \"LAST\": 2200},\n" +
            "{\"SECID\": \"LPSB\", \"LAST\": 8.6},\n" +
            "{\"SECID\": \"LSNG\", \"LAST\": 6.38},\n" +
            "{\"SECID\": \"LSNGP\", \"LAST\": 168.95},\n" +
            "{\"SECID\": \"LSRG\", \"LAST\": 756},\n" +
            "{\"SECID\": \"LVHK\", \"LAST\": 17.02},\n" +
            "{\"SECID\": \"MAGE\", \"LAST\": 7.65},\n" +
            "{\"SECID\": \"MAGEP\", \"LAST\": 5.96},\n" +
            "{\"SECID\": \"MAGN\", \"LAST\": 77.97},\n" +
            "{\"SECID\": \"MAIL\", \"LAST\": 1535},\n" +
            "{\"SECID\": \"MDMG\", \"LAST\": 851.1},\n" +
            "{\"SECID\": \"MFGS\", \"LAST\": 316},\n" +
            "{\"SECID\": \"MFGSP\", \"LAST\": 281},\n" +
            "{\"SECID\": \"MFON\", \"LAST\": null},\n" +
            "{\"SECID\": \"MGNT\", \"LAST\": 5646.5},\n" +
            "{\"SECID\": \"MGNZ\", \"LAST\": 9670},\n" +
            "{\"SECID\": \"MGTS\", \"LAST\": 2070},\n" +
            "{\"SECID\": \"MGTSP\", \"LAST\": 1386},\n" +
            "{\"SECID\": \"MISB\", \"LAST\": 13.6},\n" +
            "{\"SECID\": \"MISBP\", \"LAST\": 11},\n" +
            "{\"SECID\": \"MOEX\", \"LAST\": 178.77},\n" +
            "{\"SECID\": \"MORI\", \"LAST\": null},\n" +
            "{\"SECID\": \"MRKC\", \"LAST\": 0.4074},\n" +
            "{\"SECID\": \"MRKK\", \"LAST\": 27.74},\n" +
            "{\"SECID\": \"MRKP\", \"LAST\": 0.2639},\n" +
            "{\"SECID\": \"MRKS\", \"LAST\": 0.2925},\n" +
            "{\"SECID\": \"MRKU\", \"LAST\": 0.163},\n" +
            "{\"SECID\": \"MRKV\", \"LAST\": 0.0566},\n" +
            "{\"SECID\": \"MRKY\", \"LAST\": 0.04605},\n" +
            "{\"SECID\": \"MRKZ\", \"LAST\": 0.056},\n" +
            "{\"SECID\": \"MRSB\", \"LAST\": 0.436},\n" +
            "{\"SECID\": \"MSNG\", \"LAST\": 2.4005},\n" +
            "{\"SECID\": \"MSRS\", \"LAST\": 1.3155},\n" +
            "{\"SECID\": \"MSTT\", \"LAST\": 90.15},\n" +
            "{\"SECID\": \"MTLR\", \"LAST\": 144.79},\n" +
            "{\"SECID\": \"MTLRP\", \"LAST\": 247.05},\n" +
            "{\"SECID\": \"MTSS\", \"LAST\": 328.5},\n" +
            "{\"SECID\": \"MVID\", \"LAST\": 601.3},\n" +
            "{\"SECID\": \"NAUK\", \"LAST\": 207},\n" +
            "{\"SECID\": \"NFAZ\", \"LAST\": 434.6},\n" +
            "{\"SECID\": \"NKHP\", \"LAST\": 357},\n" +
            "{\"SECID\": \"NKNC\", \"LAST\": 144.9},\n" +
            "{\"SECID\": \"NKNCP\", \"LAST\": 116.96},\n" +
            "{\"SECID\": \"NKSH\", \"LAST\": 27.08},\n" +
            "{\"SECID\": \"NLMK\", \"LAST\": 219.98},\n" +
            "{\"SECID\": \"NMTP\", \"LAST\": 8.005},\n" +
            "{\"SECID\": \"NNSB\", \"LAST\": 2480},\n" +
            "{\"SECID\": \"NNSBP\", \"LAST\": 808},\n" +
            "{\"SECID\": \"NPOF\", \"LAST\": null},\n" +
            "{\"SECID\": \"NSVZ\", \"LAST\": 194.5},\n" +
            "{\"SECID\": \"NVTK\", \"LAST\": 1900.8},\n" +
            "{\"SECID\": \"OGKB\", \"LAST\": 0.6758},\n" +
            "{\"SECID\": \"OKEY\", \"LAST\": 49.66},\n" +
            "{\"SECID\": \"OMZZP\", \"LAST\": 4205},\n" +
            "{\"SECID\": \"ORUP\", \"LAST\": 25.7},\n" +
            "{\"SECID\": \"OZON\", \"LAST\": 3698},\n" +
            "{\"SECID\": \"PAZA\", \"LAST\": 6600},\n" +
            "{\"SECID\": \"PHOR\", \"LAST\": 5145},\n" +
            "{\"SECID\": \"PIKK\", \"LAST\": 1498.1},\n" +
            "{\"SECID\": \"PLZL\", \"LAST\": 12802},\n" +
            "{\"SECID\": \"PMSB\", \"LAST\": 155.6},\n" +
            "{\"SECID\": \"PMSBP\", \"LAST\": 154.2},\n" +
            "{\"SECID\": \"POGR\", \"LAST\": 21.37},\n" +
            "{\"SECID\": \"POLY\", \"LAST\": 1341.4},\n" +
            "{\"SECID\": \"PRMB\", \"LAST\": 42600},\n" +
            "{\"SECID\": \"QIWI\", \"LAST\": 606},\n" +
            "{\"SECID\": \"RASP\", \"LAST\": 391.88},\n" +
            "{\"SECID\": \"RAVN\", \"LAST\": 30.15},\n" +
            "{\"SECID\": \"RBCM\", \"LAST\": 3.942},\n" +
            "{\"SECID\": \"RDRB\", \"LAST\": 228},\n" +
            "{\"SECID\": \"RGSS\", \"LAST\": 0.2234},\n" +
            "{\"SECID\": \"RKKE\", \"LAST\": 7200},\n" +
            "{\"SECID\": \"RNFT\", \"LAST\": 182.4},\n" +
            "{\"SECID\": \"ROLO\", \"LAST\": 9.895},\n" +
            "{\"SECID\": \"ROSB\", \"LAST\": 78},\n" +
            "{\"SECID\": \"ROSN\", \"LAST\": 586.9},\n" +
            "{\"SECID\": \"ROST\", \"LAST\": 74.8},\n" +
            "{\"SECID\": \"RSTI\", \"LAST\": 1.285},\n" +
            "{\"SECID\": \"RSTIP\", \"LAST\": 1.932},\n" +
            "{\"SECID\": \"RTGZ\", \"LAST\": null},\n" +
            "{\"SECID\": \"RTKM\", \"LAST\": 91.33},\n" +
            "{\"SECID\": \"RTKMP\", \"LAST\": 84.55},\n" +
            "{\"SECID\": \"RTSB\", \"LAST\": 0.6},\n" +
            "{\"SECID\": \"RTSBP\", \"LAST\": 0.77},\n" +
            "{\"SECID\": \"RUAL\", \"LAST\": 73.955},\n" +
            "{\"SECID\": \"RUGR\", \"LAST\": 12.33},\n" +
            "{\"SECID\": \"RUSI\", \"LAST\": 56},\n" +
            "{\"SECID\": \"RUSP\", \"LAST\": null},\n" +
            "{\"SECID\": \"RZSB\", \"LAST\": 17.56},\n" +
            "{\"SECID\": \"SAGO\", \"LAST\": 0.752},\n" +
            "{\"SECID\": \"SAGOP\", \"LAST\": 0.656},\n" +
            "{\"SECID\": \"SARE\", \"LAST\": 0.1248},\n" +
            "{\"SECID\": \"SAREP\", \"LAST\": 0.1042},\n" +
            "{\"SECID\": \"SBER\", \"LAST\": 328.8},\n" +
            "{\"SECID\": \"SBERP\", \"LAST\": 310.78},\n" +
            "{\"SECID\": \"SELG\", \"LAST\": 43.86},\n" +
            "{\"SECID\": \"SELGP\", \"LAST\": 43.55},\n" +
            "{\"SECID\": \"SFIN\", \"LAST\": 426.6},\n" +
            "{\"SECID\": \"SGZH\", \"LAST\": 8.565},\n" +
            "{\"SECID\": \"SIBN\", \"LAST\": 453.55},\n" +
            "{\"SECID\": \"SLEN\", \"LAST\": 3.985},\n" +
            "{\"SECID\": \"SMLT\", \"LAST\": 5629},\n" +
            "{\"SECID\": \"SNGS\", \"LAST\": 32.725},\n" +
            "{\"SECID\": \"SNGSP\", \"LAST\": 38.095},\n" +
            "{\"SECID\": \"STSB\", \"LAST\": 0.755},\n" +
            "{\"SECID\": \"STSBP\", \"LAST\": 0.6185},\n" +
            "{\"SECID\": \"SVAV\", \"LAST\": 216},\n" +
            "{\"SECID\": \"TASB\", \"LAST\": 0.5375},\n" +
            "{\"SECID\": \"TASBP\", \"LAST\": 0.508},\n" +
            "{\"SECID\": \"TATN\", \"LAST\": 503.7},\n" +
            "{\"SECID\": \"TATNP\", \"LAST\": 468.9},\n" +
            "{\"SECID\": \"TCSG\", \"LAST\": 6667},\n" +
            "{\"SECID\": \"TGKA\", \"LAST\": 0.010974},\n" +
            "{\"SECID\": \"TGKB\", \"LAST\": 0.004075},\n" +
            "{\"SECID\": \"TGKBP\", \"LAST\": 0.00651},\n" +
            "{\"SECID\": \"TGKD\", \"LAST\": 0.00681},\n" +
            "{\"SECID\": \"TGKDP\", \"LAST\": 0.00578},\n" +
            "{\"SECID\": \"TGKN\", \"LAST\": 0.00287},\n" +
            "{\"SECID\": \"TNSE\", \"LAST\": 955},\n" +
            "{\"SECID\": \"TORS\", \"LAST\": 0.374},\n" +
            "{\"SECID\": \"TORSP\", \"LAST\": 0.2715},\n" +
            "{\"SECID\": \"TRCN\", \"LAST\": null},\n" +
            "{\"SECID\": \"TRFM\", \"LAST\": null},\n" +
            "{\"SECID\": \"TRMK\", \"LAST\": 96.28},\n" +
            "{\"SECID\": \"TRNFP\", \"LAST\": 164150},\n" +
            "{\"SECID\": \"TTLK\", \"LAST\": 0.635},\n" +
            "{\"SECID\": \"TUZA\", \"LAST\": 100.5},\n" +
            "{\"SECID\": \"UCSS\", \"LAST\": 1010},\n" +
            "{\"SECID\": \"UKUZ\", \"LAST\": 860},\n" +
            "{\"SECID\": \"UNAC\", \"LAST\": 0.7105},\n" +
            "{\"SECID\": \"UNKL\", \"LAST\": 8690},\n" +
            "{\"SECID\": \"UPRO\", \"LAST\": 2.765},\n" +
            "{\"SECID\": \"URKA\", \"LAST\": null},\n" +
            "{\"SECID\": \"URKZ\", \"LAST\": 16280},\n" +
            "{\"SECID\": \"USBN\", \"LAST\": 0.0892},\n" +
            "{\"SECID\": \"UTAR\", \"LAST\": 6.82},\n" +
            "{\"SECID\": \"UWGN\", \"LAST\": 111.7},\n" +
            "{\"SECID\": \"VGSB\", \"LAST\": 3.745},\n" +
            "{\"SECID\": \"VGSBP\", \"LAST\": 1.86},\n" +
            "{\"SECID\": \"VJGZ\", \"LAST\": 1878},\n" +
            "{\"SECID\": \"VJGZP\", \"LAST\": 646},\n" +
            "{\"SECID\": \"VLHZ\", \"LAST\": 75.7},\n" +
            "{\"SECID\": \"VRSB\", \"LAST\": 69.8},\n" +
            "{\"SECID\": \"VRSBP\", \"LAST\": 43.8},\n" +
            "{\"SECID\": \"VSMO\", \"LAST\": 31940},\n" +
            "{\"SECID\": \"VSYD\", \"LAST\": 4740},\n" +
            "{\"SECID\": \"VSYDP\", \"LAST\": 4760},\n" +
            "{\"SECID\": \"VTBR\", \"LAST\": 0.050465},\n" +
            "{\"SECID\": \"WTCM\", \"LAST\": 9.56},\n" +
            "{\"SECID\": \"WTCMP\", \"LAST\": 8.8},\n" +
            "{\"SECID\": \"YAKG\", \"LAST\": 124.65},\n" +
            "{\"SECID\": \"YKEN\", \"LAST\": 0.3505},\n" +
            "{\"SECID\": \"YKENP\", \"LAST\": 0.32},\n" +
            "{\"SECID\": \"YNDX\", \"LAST\": 5752.8},\n" +
            "{\"SECID\": \"YRSB\", \"LAST\": 216},\n" +
            "{\"SECID\": \"YRSBP\", \"LAST\": 92},\n" +
            "{\"SECID\": \"ZILL\", \"LAST\": 4240},\n" +
            "{\"SECID\": \"ZVEZ\", \"LAST\": 4.275}]}\n" +
            "]";

    @GetMapping("/shares/{id}")
    public Mono<String> retrive(@PathVariable Long id) {
        Mono<String> response = webClient.get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class).log();


        return  response;
    }

    @GetMapping("/shares")
    public   String retrivee() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("CustomDes", new Version(1, 0, 0, null, null, null));

        module.addDeserializer(Shares.class, new CustomDes());
        mapper.registerModule(module);
        Shares shares = mapper.readValue(jsoni, Shares.class);


        Mono<Object[]> response = webClient.get()
                .uri(uri_list)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object[].class);



        return "response";


    }

    @GetMapping("/something")
    public String handle() {
        return "dfdf";
    }
}

class CustomDes extends StdDeserializer<Shares> {
    public CustomDes() {
        this(null);
    }

    public CustomDes(Class<?> vc) {
        super(vc);
    }

    @Override
    public Shares deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Shares shares = new Shares();
        JsonNode nextElement;
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);
        if (node.path("marketdata").isMissingNode()) {
            System.out.println("There is one");
        }
        Iterator<JsonNode> rootElements = node.elements();
        while (rootElements.hasNext()) {
            nextElement = rootElements.next();
            if(nextElement.has("marketdata")) {
//                StreamSupport.stream(nextElement.path("marketdata").spliterator(), false).collect(Collectors.toMap())


            }
        }
        return shares;
    }
}





