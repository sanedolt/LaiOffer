package com.laioffer.OOD.TexasHoldem;

import java.util.*;

public class Hand implements Comparable<Hand> {
    private List<Card> cards;
    Map<Integer,Integer> rec;
    private int score;
    public Hand() {
        this.cards = new ArrayList<>();
        this.score = 0;
    }

    public int getScore() {
        return score;
    }
    public List<Card> getCards() {
        return cards;
    }

    @Override
    public int compareTo(Hand other) {
        return this.score - other.getScore();
    }

    public void clearHand() {
        cards.clear();
    }
    public void addCard(Card card) {
        cards.add(card);
    }
    private void getMap() {
        rec = new HashMap<>();
        for (Card card : cards) {
            int v = card.getValue();
            rec.put(v,rec.getOrDefault(v,0)+1);
        }
    }
    public int getConfidence() {
        // return confidence based on situation on table
        int size = cards.size();
        getMap();
        if (size==7) {calculate(); return score/1000;}
        int tmp = 0;
        if ((tmp = hasStraightFlush())>0) {
            return 90+Math.min(10,tmp/6);
        } else if ((tmp = hasFourOfKind())>0) {
            return 80+Math.max(1,tmp-4);
        } else if ((tmp = hasFullHouse())>0) {
            return 70+Math.min(10,tmp/25);
        } else if ((tmp = hasFlush())>0) {
            return 60+tmp;
        } else if ((tmp = hasStraight())>0) {
            return 50+tmp;
        } else if ((tmp = hasThreeOfKind())>0) {
            return 40+Math.max(1,tmp-4);
        } else if ((tmp = hasTwoPairs())>0) {
            return 30+Math.min(10,tmp/1000);
        } else if ((tmp = hasPair())>0) {
            return 20+Math.min(10,tmp/6);
        } else {
            return highCard()/4;
        }
    }
    private int highCard() {
        int max = 0;
        for (Card card : cards) {
            max=Math.max(max,card.getValue()* 4 + card.getSuit().ordinal());
        }
        return max;
    }
    private int hasPair() {
        if (cards.size()<2) {return 0;}
        int ret = 0;
        for (Card card : cards) {
            int v = card.getValue();
            if (rec.get(v)==2) {
                ret=Math.max(ret, v * 4 + card.getSuit().ordinal());
            }
        }
        return 0;
    }
    private int hasTwoPairs() {
        if (cards.size()<4) {return 0;}
        int ret = 0;
        int v1=-1,v2=-1,v3=-1;
        for (Map.Entry<Integer,Integer> e : rec.entrySet()) {
            if (e.getValue()==2) {
                int v = e.getKey();
                if (v1==-1) {v1=v;} else if (v2==-1) {v2=v;} else {v3=v;}
            }
        }
        if (v3>v2) {
            int tmp = v2;
            v2=v3;
            v3=tmp;
        }
        if (v2>v1) {
            int tmp = v1;
            v1=v2;
            v2=tmp;
        }
        ret=v1*13*52+v2*52;
        int max = 0;
        for (Card card : cards) {
            int v = card.getValue();
            if (v!=v1 && v!=v2) {
                max = Math.max(max, v * 4 + card.getSuit().ordinal());
            }
        }
        return ret+max;
    }
    private int hasThreeOfKind() {
        if (cards.size()<3) {return 0;}
        int v = 0;
        for (Map.Entry<Integer,Integer> e : rec.entrySet()) {
            if (e.getValue()==3) {v=Math.max(v,e.getKey());}
        }
        return v;
    }
    private int hasStraight() {
        if (cards.size()<5) {return 0;}
        int[] input = new int[cards.size()];
        for (int i=0;i<cards.size();i++) {
            input[i]=cards.get(i).getValue();
        }
        Arrays.sort(input);
        for (int i=cards.size()-5;i>=0;i--) {
            if (rec.containsKey(input[i]+1) && rec.containsKey(input[i]+2) && rec.containsKey(input[i]+3) && rec.containsKey(input[i]+4)) {
                return input[i];
            }
        }
        return 0;
    }
    private int hasFlush() {
        if (cards.size()<5) {return 0;}
        int[] count = new int[4];
        for (Card card : cards) {
            count[card.getSuit().ordinal()]++;
        }
        for (int i=0;i<4;i++) {
            if (count[i]>=5) {return i;}
        }
        return 0;
    }
    private int hasFullHouse() {
        if (cards.size()<5) {return 0;}
        int u = 0, v = 0;
        for (Map.Entry<Integer,Integer> e : rec.entrySet()) {
            if (e.getValue()==3) {v=Math.max(v,e.getKey());}
            if (e.getValue()==2) {u=Math.max(u,e.getKey());}
        }
        return u>0&&v>0?(v*20+u):0;
    }
    private int hasFourOfKind() {
        if (cards.size()<4) {return 0;}
        for (Map.Entry<Integer,Integer> e : rec.entrySet()) {
            if (e.getValue()==4) {return e.getKey();}
        }
        return 0;
    }
    private int hasStraightFlush() {
        if (cards.size()<5) {return 0;}
        int[] input = new int[cards.size()];
        Set<Integer> seen = new HashSet<>();
        for (int i=0;i<cards.size();i++) {
            input[i]=cards.get(i).getSuit().ordinal()*20+cards.get(i).getValue();
            seen.add(input[i]);
        }
        Arrays.sort(input);
        for (int i=cards.size()-5;i>=0;i--) {
            if (seen.contains(input[i]+1) && seen.contains(input[i]+2) && seen.contains(input[i]+3) && seen.contains(input[i]+4)) {
                return input[i]%20*4+input[i]/20;
            }
        }
        return 0;
    }

    public void calculate() {
        if (cards.size()<7) {
            score=0;
            return;
        }
        getMap();
        int tmp = 0;
        if ((tmp = hasStraightFlush())>0) {
            score=90000+tmp;
        } else if ((tmp = hasFourOfKind())>0) {
            score=80000+tmp;
        } else if ((tmp = hasFullHouse())>0) {
            score=70000+tmp;
        } else if ((tmp = hasFlush())>0) {
            score=60000+tmp;
        } else if ((tmp = hasStraight())>0) {
            score=50000+tmp;
        } else if ((tmp = hasThreeOfKind())>0) {
            score=40000+tmp;
        } else if ((tmp = hasTwoPairs())>0) {
            score=30000+tmp;
        } else if ((tmp = hasPair())>0) {
            score=20000+tmp;
        } else {
            score=10000+tmp;
        }
    }

}

