package com.milkman.api.fleet_connector;

import clojure.lang.PersistentList;
import clojure.lang.PersistentVector;
import com.milkman.core.factories.BestFitFactory;

import java.util.ArrayList;
import java.util.List;

public class BestFitManager {

    /**
     * (defn find-duplicates
     *   "Finds all duplicated elements in a collection"
     *   ([v] (find-duplicates (sort v) '()))
     *   ([sorted-v dupes] (if-let [head (first sorted-v)]
     *                       (recur (rest sorted-v)
     *                              (if (and (= head (second sorted-v))
     *                                       (not= head (first dupes)))
     *                                (conj dupes head)
     *                                dupes))
     *                       dupes)))
     * @param in
     * @return
     */
    public static PersistentList findDuplicates(PersistentList in) {
         List out = BestFitFactory.iBestFit().findDuplicates(new ArrayList(in));
         return new PersistentList(out);
    }

    /**
     * same as above, but accepts a PersistentVector
     * @param in
     * @return
     */
    public static PersistentVector findDuplicates(PersistentVector in) {
        List out = BestFitFactory.iBestFit().findDuplicates(new ArrayList(in));
        return PersistentVector.create(out);
    }

}
