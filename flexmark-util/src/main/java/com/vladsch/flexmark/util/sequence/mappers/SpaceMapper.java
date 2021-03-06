package com.vladsch.flexmark.util.sequence.mappers;

import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import org.jetbrains.annotations.NotNull;

public class SpaceMapper {
    public static final CharMapper toNonBreakSpace = new ToNonBreakSpace();
    public static final CharMapper fromNonBreakSpace = new FromNonBreakSpace();

    public static @NotNull CharMapper toSpaces(@NotNull CharPredicate predicate) {
        return new FromPredicate(predicate);
    }

    private static class FromNonBreakSpace implements CharMapper {
        FromNonBreakSpace() {}

        @Override
        public char map(char c) {
            return c == SequenceUtils.NBSP ? SequenceUtils.SPC : c;
        }
    }

    private static class FromPredicate implements CharMapper {
        final @NotNull CharPredicate myPredicate;

        FromPredicate(@NotNull CharPredicate predicate) {
            myPredicate = predicate;
        }

        @Override
        public char map(char c) {
            return myPredicate.test(c) ? SequenceUtils.SPC : c;
        }
    }

    private static class ToNonBreakSpace implements CharMapper {
        ToNonBreakSpace() {}

        @Override
        public char map(char c) {
            return c == SequenceUtils.SPC ? SequenceUtils.NBSP : c;
        }
    }
}
