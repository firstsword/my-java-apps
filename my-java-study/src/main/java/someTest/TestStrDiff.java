package someTest;

import org.junit.Test;

/**
 * Created by firstsword on 2019/3/5.
 */
public class TestStrDiff {
    @Test
    public void t1() {
        String s1 = getStr1();
        String s2 = getStr2();

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                System.out.println("diff at: " + i);
                System.out.println("s1 is: " + s1.charAt(i-1)+s1.charAt(i)+s1.charAt(i+1));
                System.out.println("s2 is: " + s2.charAt(i-1)+s2.charAt(i)+s2.charAt(i+1));
                System.out.println("-----------------");
                System.out.println();
            }
        }
    }

    public static String getStr1() {
        String s = "【KafkaOffsetBackingStore:136】offset:[\"zabbix-c-1\",{\"metric\":\"offset\"}]:{\"offset_key_value_map\":\"25466:1551866713,25587:1551866713,25586:1551866713,25465:1551866713,25589:1551866713,25468:1551866713,25588:1551866713,25467:1551866713,25469:1551866713,25581:1551866713,25460:1551866713,25580:1551866713,25583:1551866713,25462:1551866713,25582:1551866713,25461:1551866713,25585:1551866713,25464:1551866713,25584:1551866713,25463:1551866713,25598:1551866713,25477:1551866713,25476:1551866713,25597:1551866713,25479:1551866713,25478:1551866713,25599:1551866713,25590:1551866713,25471:1551866713,25592:1551866713,25470:1551866713,25591:1551866713,25594:1551866713,25473:1551866713,25472:1551866713,25593:1551866713,25596:1551866713,25475:1551866713,25595:1551866713,25474:1551866713,25565:1551866713,25444:1551866713,25564:1551866713,25443:1551866713,25446:1551866713,25567:1551866713,25566:1551866713,25445:1551866713,25569:1551866713,25447:1551866713,25568:1551866713,25440:1551866713,25561:1551866713,25560:1551866713,25442:1551866713,25563:1551866713,25441:1551866713,25562:1551866713,25576:1551866713,25575:1551866713,25578:1551866713,25457:1551866713,25577:1551866713,25459:1551866713,25579:1551866713,25458:1551866713,25570:1551866713,25572:1551866713,25571:1551866713,25574:1551866713,25573:1551866713,25429:1551866713,25422:1551866713,25421:1551866713,25424:1551866713,25423:1551866713,25426:1551866713,25547:1551866713,25425:1551866713,25428:1551866713,25549:1551866713,25427:1551866713,25548:1551866713,25420:1551866713,25433:1551866713,25554:1551866713,25432:1551866713,25553:1551866713,25435:1551866713,25556:1551866713,25434:1551866713,25555:1551866713,25437:1551866713,25558:1551866713,25436:1551866713,25557:1551866713,25439:1551866713,25438:1551866713,25559:1551866713,25550:1551866713,25431:1551866713,25552:1551866713,25430:1551866713,25551:1551866713,25529:1551866713,25528:1551866713,25400:1551866713,25521:1551866713,25520:1551866713,25402:1551866713,25523:1551866713,25401:1551866713,25522:1551866713,25404:1551866713,25525:1551866713,25403:1551866713,25524:1551866713,25406:1551866713,25527:1551866713,25405:1551866713,25526:1551866713,25419:1551866713,25418:1551866713,25417:1551866713,25416:1551866713,25749:1551866713,25507:1551866713,25748:1551870218,25506:1551866713,25509:1551866713,25508:1551866713,25741:1551866713,25740:1551866713,25743:1551866713,25501:1551866713,25742:1551866713,25500:1551866713,25745:1551866713,25503:1551866713,25744:1551866713,25502:1551866713,25505:1551866713,25746:1551866713,25504:1551866713,25518:1551866713,25517:1551866713,25519:1551866713,25510:1551866713,25512:1551866713,25511:1551866713,25514:1551866713,25513:1551866713,25516:1551866713,25515:1551866713,25727:1551866713,25606:1551866713,25605:1551866713,25729:1551866713,25608:1551866713,25728:1551866713,25607:1551866713,25609:1551866713,25600:1551866713,25602:1551866713,25601:1551866713,25604:1551866713,25725:1551866713,25603:1551866713,25738:1551866713,25617:1551866713,25737:1551866713,25616:1551866713,25619:1551866713,25739:1551866713,25618:1551866713,25730:1551866713,25732:1551866713,25611:1551866713,25731:1551866713,25610:1551866713,25734:1551866713,25613:1551866713,25733:1551866713,25612:1551866713,25736:1551866713,25615:1551866713,25735:1551866713,25614:1551866713,25705:1551866713,25704:1551866713,25706:1551866713,25389:1551866713,25388:1551866713,25701:1551866713,25703:1551866713,25702:1551866713,25381:1551866713,25380:1551866713,25383:1551866713,25382:1551866713,25385:1551866713,25384:1551866713,25387:1551866713,25386:1551866713,25399:1551866713,25392:1551866713,25391:1551866713,25394:1551866713,25393:1551866713,25396:1551866713,25395:1551866713,25398:1551866713,25397:1551866713,25390:1551866713,25488:1551866713,25487:1551866713,25480:1551866713,25482:1551866713,25481:1551866713,25484:1551866713,25483:1551866713,25486:1551866713,25485:1551866713,25378:1551866713,25499:1551866713,25377:1551866713,25498:1551866713,25379:1551866713,25376:1551866713,25375:1551866713,\"}\n";
        return s;
    }

    public static String getStr2() {
        String s = "【KafkaOffsetBackingStore:136】offset:[\"zabbix-c-1\",{\"metric\":\"offset\"}]:{\"offset_key_value_map\":\"25466:1551866713,25587:1551866713,25586:1551866713,25465:1551866713,25589:1551866713,25468:1551866713,25588:1551866713,25467:1551866713,25469:1551866713,25581:1551866713,25460:1551866713,25580:1551866713,25583:1551866713,25462:1551866713,25582:1551866713,25461:1551866713,25585:1551866713,25464:1551866713,25584:1551866713,25463:1551866713,25598:1551866713,25477:1551866713,25476:1551866713,25597:1551866713,25479:1551866713,25478:1551866713,25599:1551866713,25590:1551866713,25471:1551866713,25592:1551866713,25470:1551866713,25591:1551866713,25594:1551866713,25473:1551866713,25472:1551866713,25593:1551866713,25596:1551866713,25475:1551866713,25595:1551866713,25474:1551866713,25565:1551866713,25444:1551866713,25564:1551866713,25443:1551866713,25446:1551866713,25567:1551866713,25566:1551866713,25445:1551866713,25569:1551866713,25447:1551866713,25568:1551866713,25440:1551866713,25561:1551866713,25560:1551866713,25442:1551866713,25563:1551866713,25441:1551866713,25562:1551866713,25576:1551866713,25575:1551866713,25578:1551866713,25457:1551866713,25577:1551866713,25459:1551866713,25579:1551866713,25458:1551866713,25570:1551866713,25572:1551866713,25571:1551866713,25574:1551866713,25573:1551866713,25429:1551866713,25422:1551866713,25421:1551866713,25424:1551866713,25423:1551866713,25426:1551866713,25547:1551866713,25425:1551866713,25428:1551866713,25549:1551866713,25427:1551866713,25548:1551866713,25420:1551866713,25433:1551866713,25554:1551866713,25432:1551866713,25553:1551866713,25435:1551866713,25556:1551866713,25434:1551866713,25555:1551866713,25437:1551866713,25558:1551866713,25436:1551866713,25557:1551866713,25439:1551866713,25438:1551866713,25559:1551866713,25550:1551866713,25431:1551866713,25552:1551866713,25430:1551866713,25551:1551866713,25529:1551866713,25528:1551866713,25400:1551866713,25521:1551866713,25520:1551866713,25402:1551866713,25523:1551866713,25401:1551866713,25522:1551866713,25404:1551866713,25525:1551866713,25403:1551866713,25524:1551866713,25406:1551866713,25527:1551866713,25405:1551866713,25526:1551866713,25419:1551866713,25418:1551866713,25417:1551866713,25416:1551866713,25749:1551866713,25507:1551866713,25748:1551870158,25506:1551866713,25509:1551866713,25508:1551866713,25741:1551866713,25740:1551866713,25743:1551866713,25501:1551866713,25742:1551866713,25500:1551866713,25745:1551866713,25503:1551866713,25744:1551866713,25502:1551866713,25505:1551866713,25746:1551866713,25504:1551866713,25518:1551866713,25517:1551866713,25519:1551866713,25510:1551866713,25512:1551866713,25511:1551866713,25514:1551866713,25513:1551866713,25516:1551866713,25515:1551866713,25727:1551866713,25606:1551866713,25605:1551866713,25729:1551866713,25608:1551866713,25728:1551866713,25607:1551866713,25609:1551866713,25600:1551866713,25602:1551866713,25601:1551866713,25604:1551866713,25725:1551866713,25603:1551866713,25738:1551866713,25617:1551866713,25737:1551866713,25616:1551866713,25619:1551866713,25739:1551866713,25618:1551866713,25730:1551866713,25732:1551866713,25611:1551866713,25731:1551866713,25610:1551866713,25734:1551866713,25613:1551866713,25733:1551866713,25612:1551866713,25736:1551866713,25615:1551866713,25735:1551866713,25614:1551866713,25705:1551866713,25704:1551866713,25706:1551866713,25389:1551866713,25388:1551866713,25701:1551866713,25703:1551866713,25702:1551866713,25381:1551866713,25380:1551866713,25383:1551866713,25382:1551866713,25385:1551866713,25384:1551866713,25387:1551866713,25386:1551866713,25399:1551866713,25392:1551866713,25391:1551866713,25394:1551866713,25393:1551866713,25396:1551866713,25395:1551866713,25398:1551866713,25397:1551866713,25390:1551866713,25488:1551866713,25487:1551866713,25480:1551866713,25482:1551866713,25481:1551866713,25484:1551866713,25483:1551866713,25486:1551866713,25485:1551866713,25378:1551866713,25499:1551866713,25377:1551866713,25498:1551866713,25379:1551866713,25376:1551866713,25375:1551866713,\"}\n";
        return s;
    }
}
