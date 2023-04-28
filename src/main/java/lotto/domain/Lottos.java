package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
    public static long LOTTO_PRICE = 1_000;
    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos(long price, LottoType auto) {
        if (price < LOTTO_PRICE) {
            throw new IllegalArgumentException("로또는 " + LOTTO_PRICE + "의 금액부터 구매가 가능합니다. 주문금액: " + price);
        }

        for (int i = 0; i < price / LOTTO_PRICE; i++) {
            this.lottos.add(new Lotto(auto));
        }
    }

    public Lottos(long price, List<Lotto> manualNumbers) {
        long autoPrice = price - (manualNumbers.size() * LOTTO_PRICE);
        this.lottos.addAll(manualNumbers);

        for (int i = 0; i < autoPrice / LOTTO_PRICE; i++) {
            this.lottos.add(new Lotto(LottoType.AUTO));
        }
    }

    public Lottos(Lotto... lottos) {
        Collections.addAll(this.lottos, lottos);
    }

    public List<Lotto> lottos() {
        return Collections.unmodifiableList(this.lottos);
    }

    public long purchaseAmount() {
        return LOTTO_PRICE * lottos().size();
    }

    public long purchaseQuantity() {
        return lottos.stream().filter(Lotto::isAutomaticPurchasedLotto).count();
    }

    public long manualPurchaseQuantity() {
        return lottos.stream().filter(Lotto::isManualPurchasedLotto).count();
    }

    public List<WinType> confirmWins(WinNumbers winNumbers) {
        List<WinType> winTypes = new ArrayList<>();
        for (Lotto lotto : lottos()) {
            winTypes.add(winNumbers.confirmWin(lotto));
        }

        return winTypes;
    }
}
