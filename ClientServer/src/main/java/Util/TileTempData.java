package Util;

import components.AnimationState;
import components.StateMachine;
import jade.RawObject;

import java.util.HashMap;

public class TileTempData {
//    private static int[] animatedTileIndex = {270, 271, 272, 273, 275, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 306, 307, 308, 309, 311, 312, 313, 314, 316, 317, 318, 319, 321, 322, 323, 324, 325, 326, 327, 329, 331, 332, 333, 336, 337, 338, 341, 342, 343, 346, 347, 348, 351, 352, 353, 354, 598};

    public static HashMap<Integer, Integer[]> tileIndexToAnimated = new HashMap<>() {
        {
            put(270, new Integer[]{100, 270, 378, 486, 594});
            put(271, new Integer[]{100, 271, 379, 487, 595});
            put(272, new Integer[]{100, 272, 380, 488, 596});
            put(273, new Integer[]{100, 273, 381, 489, 597});
            put(275, new Integer[]{100, 275, 356, 437, 518});
            put(277, new Integer[]{100, 277, 358, 439, 520});
            put(278, new Integer[]{100, 278, 359, 440, 521});
            put(279, new Integer[]{100, 279, 360, 441, 522});
            put(280, new Integer[]{100, 280, 334, 388, 442});
            put(281, new Integer[]{100, 281, 335, 389, 443});
            put(282, new Integer[]{400, 282, 363, 444, 525});
            put(283, new Integer[]{400, 283, 364, 445, 526});
            put(284, new Integer[]{400, 284, 365, 446, 527});
            put(285, new Integer[]{400, 285, 339, 393, 447});
            put(286, new Integer[]{400, 286, 340, 394, 448});
            put(287, new Integer[]{200, 287, 368, 449, 530});
            put(288, new Integer[]{200, 288, 369, 450, 531});
            put(289, new Integer[]{200, 289, 370, 451, 532});
            put(290, new Integer[]{200, 290, 371, 452, 533});
            put(291, new Integer[]{200, 291, 372, 453, 534});
            put(292, new Integer[]{200, 292, 373, 454, 535});
            put(293, new Integer[]{200, 293, 374, 455, 536});
            put(294, new Integer[]{200, 294, 375, 456, 537});
            put(295, new Integer[]{200, 295, 376, 457, 538});
            put(296, new Integer[]{200, 296, 377, 458, 539});
            put(297, new Integer[]{100, 297, 405, 513, 621});
            put(298, new Integer[]{100, 298, 406, 514, 622});
            put(299, new Integer[]{100, 299, 407, 515, 623});
            put(300, new Integer[]{100, 300, 408, 516, 624});
            put(301, new Integer[]{100, 301, 382, 463, 544});
            put(302, new Integer[]{100, 302, 383, 464, 545});
            put(303, new Integer[]{100, 303, 384, 465, 546});
            put(304, new Integer[]{100, 304, 385, 466, 547});
            put(306, new Integer[]{100, 306, 387, 468, 549});
            put(307, new Integer[]{100, 307, 361, 415, 469});
            put(308, new Integer[]{100, 308, 362, 416, 470});
            put(309, new Integer[]{400, 309, 390, 471, 552});
            put(311, new Integer[]{400, 311, 392, 473, 554});
            put(312, new Integer[]{400, 312, 366, 420, 474});
            put(313, new Integer[]{400, 313, 367, 421, 475});
            put(314, new Integer[]{200, 314, 395, 476, 557});
            put(316, new Integer[]{200, 316, 397, 478, 559});
            put(317, new Integer[]{200, 317, 398, 479, 560});
            put(318, new Integer[]{200, 318, 399, 480, 561});
            put(319, new Integer[]{200, 319, 400, 481, 562});
            put(321, new Integer[]{200, 321, 402, 483, 564});
            put(322, new Integer[]{200, 322, 403, 484, 565});
            put(323, new Integer[]{200, 323, 404, 485, 566});
            put(324, new Integer[]{100, 324, 432, 540, 648});
            put(325, new Integer[]{100, 325, 433, 541, 649});
            put(326, new Integer[]{100, 326, 434, 542, 650});
            put(327, new Integer[]{100, 327, 435, 543, 651});
            put(329, new Integer[]{100, 329, 410, 491, 572});
            put(331, new Integer[]{100, 331, 412, 493, 574});
            put(332, new Integer[]{100, 332, 413, 494, 575});
            put(333, new Integer[]{100, 333, 414, 495, 576});
            put(336, new Integer[]{400, 336, 417, 498, 579});
            put(337, new Integer[]{400, 337, 418, 499, 580});
            put(338, new Integer[]{400, 338, 419, 500, 581});
            put(341, new Integer[]{200, 341, 422, 503, 584});
            put(342, new Integer[]{200, 342, 423, 504, 585});
            put(343, new Integer[]{200, 343, 424, 505, 586});
            put(346, new Integer[]{200, 346, 427, 508, 589});
            put(347, new Integer[]{200, 347, 428, 509, 590});
            put(348, new Integer[]{200, 348, 429, 510, 591});
            put(351, new Integer[]{100, 351, 459, 567, 675});
            put(352, new Integer[]{100, 352, 460, 568, 676});
            put(353, new Integer[]{100, 353, 461, 569, 677});
            put(354, new Integer[]{100, 354, 462, 570, 678});
            put(598, new Integer[]{100, 598, 599, 600, 601});
        }
    };

    public static String[] COLLISION_DATA = {
            "0","0","0","0","0","0","0","n","0","n","0","0","0","0","0","0","0","0","0","n","0","n","0","0","0","0","0",
            "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0",
            "0","0","0","0","0","0","0","n","0","n","0","0","0","0","0","0","0","0","0","n","0","n","0","0","0","0","0",

            "n","n","n","0","0","0","0","n","n","n","n","n","n","n","n","0","0","0","0","n","n","n","n","n","n","n","n",

            "2","2","2","6","4","n","0","n","n","0","n","3","2","1","a","a","n","0","n","a","a","n","n","n","n","n","n",
            "6","0","4","a","a","0","n","0","0","n","0","6","0","4","a","a","0","n","0","a","a","n","n","n","n","n","n",
            "9","8","7","a","a","n","0","n","n","0","n","9","8","7","a","a","n","0","n","n","n","n","n","n","n","n","n",

            "0","0","0","0","0","0","a","a","2","0","0","0","0","0","0","a","a","2","n","n","n","n","n","n","n","n","n",
            "a","a","a","a","a","a","a","a","2","a","a","a","a","a","a","a","a","2","n","n","n","n","n","n","n","n","n",
            "a","a","a","a","a","a","a","a","2","a","a","a","a","a","a","a","a","2","n","n","n","n","n","n","n","n","n",

            "a","a","a","a","n","a","n","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
            "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
            "a","a","a","a","n","a","n","a","a","a","a","a","a","a","a","a","a","a","a","a","n","n","a","a","a","n","n",
            "a","a","a","a","n","a","n","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
            "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
            "a","a","a","a","n","a","n","a","a","a","a","a","a","a","a","a","a","a","a","a","n","n","a","a","a","n","n",
            "a","a","a","a","n","a","n","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
            "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
            "a","a","a","a","n","a","n","a","a","a","a","a","a","a","a","a","a","a","a","a","n","n","a","a","a","n","n",

            "a","a","a","a","n","a","n","a","a","a","n","n","a","a","a","n","n","a","a","a","a","a","a","a","a","a","a",
            "a","a","a","a","a","a","a","a","a","a","n","n","a","a","a","n","n","a","a","a","a","a","a","a","a","a","a",
            "a","a","a","a","n","a","n","a","a","a","n","n","a","a","a","n","n","a","a","a","n","n","a","a","a","n","n",

            "a","a","a","a","a","a","a","a","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n",
            "a","a","a","a","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n",
            "a","a","a","a","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n",
            "a","a","a","a","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n",

            "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
            "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
            "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
            "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",

            "a","a","n","n","a","a","a","a","a","a","a","a","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n",
            "a","a","n","n","a","a","a","a","a","a","a","a","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n",
            "n","n","n","n","n","n","n","n","n","n","a","a","a","a","a","n","n","n","n","n","n","n","n","n","n","n","n",

            "n","n","n","n","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","n",
            "n","n","n","n","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","n",
            "n","n","n","n","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","n",
            "n","n","n","n","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","n",
            "n","n","n","n","a","a","a","a","a","a","n","n","n","n","a","a","a","a","a","a","a","a","n","n","n","n","n",



    };

    public static boolean shouldAnimate(int id) {
        return tileIndexToAnimated.containsKey(id);
    }

    public static <T extends RawObject> void addAnimations(T ro, int id, Spritesheet mapSprites) {

        Integer[] array = tileIndexToAnimated.get(id);

        AnimationState anime = new AnimationState();
        anime.title = "anime";
        for (int i=1; i < array.length-1; i++) {
            anime.addFrame(mapSprites.getSprite(array[i]), (float)array[0] / 250);
        }
        anime.setLoop(true);

        StateMachine stateMachine = new StateMachine();
        stateMachine.addState(anime);
        stateMachine.setDefaultState(anime.title);
        ro.addComponent(stateMachine);
    }
}
